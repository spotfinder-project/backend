package com.project.trash.member.repository;

import com.project.trash.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsBySocialIdAndValid(String socialId, Boolean valid);

  boolean existsByNicknameAndValid(String nickname, Boolean valid);

  Optional<Member> findByMemberIdAndValid(Long id, Boolean valid);

  Optional<Member> findBySocialIdAndValid(String socialId, Boolean valid);
}
