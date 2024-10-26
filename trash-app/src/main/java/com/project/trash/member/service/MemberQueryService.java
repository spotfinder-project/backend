package com.project.trash.member.service;

import com.project.trash.common.exception.ValidationException;
import com.project.trash.member.domain.Member;
import com.project.trash.member.domain.MemberDetail;
import com.project.trash.member.repository.MemberRepository;
import com.project.trash.member.response.MemberDetailResponse;
import com.project.trash.token.domain.Token;
import com.project.trash.token.repository.TokenRepository;
import com.project.trash.utils.MemberUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.MemberResultCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

  private final MemberRepository memberRepository;
  private final TokenRepository tokenRepository;

  public MemberDetailResponse getDetail() {
    MemberDetail member = MemberUtils.getMember();
    return new MemberDetailResponse(member.getNickname(), member.getSocialId());
  }

  @Transactional(readOnly = true)
  public Member getOne(String socialId) {
    return memberRepository.findBySocialIdAndValid(socialId, Boolean.TRUE)
                           .orElseThrow(() -> new ValidationException(MEMBER_NOT_FOUND));
  }

  @Transactional(readOnly = true)
  public Optional<Token> getToken(String socialId) {
    return tokenRepository.findByMemberId(socialId);
  }

  @Transactional(readOnly = true)
  public boolean isExistSocialId(String socialId) {
    return memberRepository.existsBySocialIdAndValid(socialId, Boolean.TRUE);
  }

  @Transactional(readOnly = true)
  public boolean isExistNickname(String nickname) {
    return memberRepository.existsByNicknameAndValid(nickname, Boolean.TRUE);
  }
}
