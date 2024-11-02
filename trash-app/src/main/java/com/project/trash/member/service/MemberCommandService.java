package com.project.trash.member.service;

import com.project.trash.auth.client.SocialApiClientComposite;
import com.project.trash.auth.domain.OAuthMember;
import com.project.trash.auth.service.JwtService;
import com.project.trash.common.domain.enums.Valid;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.member.domain.Member;
import com.project.trash.member.domain.enums.SocialType;
import com.project.trash.member.repository.MemberRepository;
import com.project.trash.member.request.LoginRequest;
import com.project.trash.member.request.MemberDeleteRequest;
import com.project.trash.member.request.ReissueRequest;
import com.project.trash.member.response.ReissueTokenResponse;
import com.project.trash.member.response.LoginResponse;
import com.project.trash.token.domain.Token;
import com.project.trash.token.repository.TokenRepository;
import com.project.trash.utils.MemberUtils;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_OAUTH_ACCESS_TOKEN_INVALID;
import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_TOKEN_INVALID;
import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_TOKEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

  private final JwtService jwtService;
  private final SocialApiClientComposite socialApiClient;

  private final MemberQueryService memberQueryService;
  private final MemberRepository memberRepository;
  private final TokenRepository tokenRepository;

  @Transactional
  public LoginResponse login(LoginRequest param) {
    String socialId = param.getSocialId();
    SocialType socialType = SocialType.fromCode(param.getSocialType());
    Member member;
    if (!memberRepository.existsBySocialIdAndValid(socialId, Boolean.TRUE)) {
      OAuthMember memberInfo = socialApiClient.getMemberInfo(socialType, param.getAccessToken());

      // 소셜 ID 일치여부 검증
      if (!socialId.equals(memberInfo.socialId())) {
        throw new ValidationException(AUTH_OAUTH_ACCESS_TOKEN_INVALID);
      }

      member = memberRepository.save(
          new Member(memberInfo.email(), memberInfo.gender(), memberInfo.socialId(), memberInfo.socialType()));
    } else {
      // 엑세스 토큰 유효성 검증
      if (!socialId.equals(socialApiClient.getSocialId(socialType, param.getAccessToken()))) {
        throw new ValidationException(AUTH_OAUTH_ACCESS_TOKEN_INVALID);
      }

      member = memberQueryService.getOne(socialId);
    }

    Pair<String, Long> accessToken = jwtService.createAccessToken(socialId);
    Pair<String, Long> refreshToken = jwtService.createRefreshToken(socialId);

    tokenRepository.save(new Token(socialId, accessToken.getLeft(), refreshToken.getLeft()));

    return new LoginResponse(socialId, Valid.convertToCode(member.getAgreementYn()), accessToken.getLeft(), accessToken.getRight(), refreshToken.getLeft(),
        refreshToken.getRight());
  }

  /**
   * 로그아웃
   */
  @Transactional
  public void logout() {
    tokenRepository.delete(getToken(MemberUtils.getMember().getSocialId()));
  }

  @Transactional
  public ReissueTokenResponse reissue(ReissueRequest param) {
    Member member = memberQueryService.getOne(param.getSocialId());

    Token token = getToken(member.getSocialId());

    if (!jwtService.isTokenValid(param.getRefreshToken(), member) ||
        !token.getRefreshToken().equals(param.getRefreshToken())) {
      throw new ValidationException(AUTH_TOKEN_INVALID);
    }

    Pair<String, Long> accessToken = jwtService.createAccessToken(member.getSocialId());
    Pair<String, Long> refreshToken = jwtService.createRefreshToken(member.getSocialId());

    token.updateToken(accessToken.getLeft(), refreshToken.getLeft());

    return ReissueTokenResponse.builder()
        .accessToken(accessToken.getLeft())
        .accessExpiration(accessToken.getRight())
        .refreshToken(refreshToken.getLeft())
        .refreshExpiration(refreshToken.getRight())
        .build();
  }

  @Transactional
  public void delete(MemberDeleteRequest param) {
    Member member = memberQueryService.getOne(MemberUtils.getMember().getSocialId());

    socialApiClient.unlink(member.getSocialType(), param.getAccessToken());
    member.delete();
    tokenRepository.delete(getToken(MemberUtils.getMember().getSocialId()));
  }

  private Token getToken(String socialId) {
    return memberQueryService.getToken(socialId)
                             .orElseThrow(() -> new ValidationException(AUTH_TOKEN_NOT_FOUND));
  }
}
