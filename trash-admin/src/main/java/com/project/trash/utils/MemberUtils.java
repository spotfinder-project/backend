package com.project.trash.utils;

import com.project.trash.member.domain.MemberDetail;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class MemberUtils {

  public MemberDetail getMember() {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof MemberDetail member) {
      return member;
    }
    return null;
  }

  public Long getMemberId() {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof MemberDetail member) {
      return member.getMemberId();
    }
    return null;
  }
}
