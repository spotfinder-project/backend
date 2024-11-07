package com.project.trash.member.service;

import com.project.trash.member.domain.Member;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberCommandService {

  private final MemberQueryService memberQueryService;

  @Transactional
  public void delete(Long memberId) {
    Member member = memberQueryService.getOne(memberId);
    member.delete();
  }
}
