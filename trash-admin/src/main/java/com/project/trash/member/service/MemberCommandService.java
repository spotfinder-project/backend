package com.project.trash.member.service;

import com.project.trash.auth.service.JwtService;
import com.project.trash.common.domain.enums.Valid;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.utils.CookieUtils;
import com.project.trash.member.domain.Member;

import com.project.trash.member.domain.OAuthMember;
import com.project.trash.member.domain.enums.Role;
import com.project.trash.member.domain.enums.SocialType;
import com.project.trash.member.repository.MemberRepository;
import com.project.trash.member.request.MemberLoginRequest;
import com.project.trash.member.sns.apple.AppleService;
import com.project.trash.member.sns.client.SocialApiClientComposite;
import com.project.trash.token.domain.Token;
import com.project.trash.token.repository.TokenRepository;
import com.project.trash.utils.MemberUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_SOCIAL_ID_INVALID;
import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_TOKEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

  private final JwtService jwtService;
  private final SocialApiClientComposite socialApiClient;
  private final AppleService appleService;

  private final MemberQueryService memberQueryService;
  private final TokenRepository tokenRepository;

  @Transactional
  public void login(MemberLoginRequest param, HttpServletResponse response) {
    SocialType socialType = SocialType.fromCode(param.getSocialType());

    OAuthMember oauthMember;
    if (socialType == SocialType.APPLE) {
      oauthMember = appleService.getMemberInfo(param.getAuthCode());
    } else {
      oauthMember = socialApiClient.getMemberInfo(socialType, socialApiClient.getAccessToken(socialType, param.getAuthCode()));
    }
    Member member = memberQueryService.getOne(oauthMember.socialId());

    Pair<String, Long> accessToken = jwtService.createAccessToken(member.getSocialId(), Role.MEMBER);
    Pair<String, Long> refreshToken = jwtService.createRefreshToken(member.getSocialId());

    tokenRepository.save(new Token(member.getSocialId(), accessToken.getLeft(), refreshToken.getLeft()));

    CookieUtils.setCookie("accessToken", accessToken.getLeft(), accessToken.getRight(), response);
    CookieUtils.setCookie("refreshToken", refreshToken.getLeft(), refreshToken.getRight(), response);
  }

  @Transactional
  public void unlink(String authCode) {
    Member member = memberQueryService.getOne(MemberUtils.getMember().getSocialId());

    if (member.getSocialType() == SocialType.APPLE) {
      appleService.revoke(authCode);
    } else {
      String accessToken = socialApiClient.getAccessToken(member.getSocialType(), authCode);
      socialApiClient.unlink(member.getSocialType(), accessToken);
    }

    member.delete();
    tokenRepository.delete(getToken(MemberUtils.getMember().getSocialId()));
  }

  @Transactional
  public void delete(Long memberId) {
    Member member = memberQueryService.getOne(memberId);
    member.delete();
  }

  private Token getToken(String socialId) {
    return memberQueryService.getToken(socialId)
            .orElseThrow(() -> new ValidationException(AUTH_TOKEN_NOT_FOUND));
  }
}
