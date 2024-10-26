package com.project.trash.member.service;

import com.project.trash.common.exception.ValidationException;
import com.project.trash.member.dao.MemberDao;
import com.project.trash.member.domain.Member;
import com.project.trash.member.repository.MemberRepository;
import com.project.trash.member.request.MemberListRequest;
import com.project.trash.member.request.MemberSignupHistoryRequest;
import com.project.trash.member.response.MemberDetailResponse;
import com.project.trash.member.response.MemberListResponse;
import com.project.trash.member.response.MemberSignupHistoryResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.MemberResultCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberQueryService {

  private final MemberRepository memberRepository;
  private final MemberDao memberDao;

  @Transactional(readOnly = true)
  public MemberDetailResponse getDetail(Long memberId) {
    return new MemberDetailResponse(getOne(memberId));
  }

  @Transactional(readOnly = true)
  public Pair<List<MemberListResponse>, Long> getList(MemberListRequest param) {
    return Pair.of(memberDao.select(param), memberDao.count(param));
  }

  public List<MemberSignupHistoryResponse> getList(MemberSignupHistoryRequest param) {
    return memberDao.select(param);
  }

  @Transactional(readOnly = true)
  public Member getOne(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(() -> new ValidationException(MEMBER_NOT_FOUND));
  }

  @Transactional(readOnly = true)
  public void verifyExist(Long id) {
    if (!memberRepository.existsById(id)) {
      throw new ValidationException(MEMBER_NOT_FOUND);
    }
  }
}
