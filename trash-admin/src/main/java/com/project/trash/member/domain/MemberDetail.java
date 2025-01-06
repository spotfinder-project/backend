package com.project.trash.member.domain;

import com.project.trash.member.domain.enums.Role;
import com.project.trash.member.domain.enums.SocialType;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class MemberDetail implements UserDetails {

  private final Long memberId;
  private final String socialId;
  private final SocialType socialType;

  public MemberDetail(Member member) {
    this.memberId = member.getMemberId();
    this.socialId = member.getSocialId();
    this.socialType = member.getSocialType();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(Role.MEMBER.getCode()));
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return this.socialId;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
