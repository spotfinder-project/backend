package com.project.trash.utils;

import com.project.trash.admin.domain.AdminDetail;

import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminUtils {

  /**
   * 로그인 관리자 정보 조회
   *
   * @return 관리자 정보
   */
  public AdminDetail getAdmin() {
    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof AdminDetail admin) {
      return admin;
    }
    return null;
  }

  public String getId() {
    return Optional.ofNullable(getAdmin()).map(AdminDetail::getId).orElse(null);
  }
}
