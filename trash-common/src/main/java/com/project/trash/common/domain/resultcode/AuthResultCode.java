package com.project.trash.common.domain.resultcode;

import com.project.trash.common.domain.ResultCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthResultCode implements ResultCode {

  AUTH_TOKEN_NOT_FOUND("AUTH000", "토큰 정보가 존재하지 않습니다."),
  AUTH_TOKEN_INVALID("AUTH001", "토큰 정보가 유효하지 않습니다."),
  AUTH_SOCIAL_ID_INVALID("AUTH002", "소셜 ID가 유효하지 않습니다."),
  AUTH_OAUTH_GET_ACCESS_TOKEN_FAIL("AUTH003", "OAuth 엑세스 토큰 발급을 실패했습니다."),
  AUTH_OAUTH_GET_MEMBER_FAIL("AUTH004", "소셜 서비스의 회원 정보 조회를 실패했습니다."),
  AUTH_APPLE_REVOKE_FAIL("AUTH005", "애플 회원 탈퇴를 실패했습니다.");

  private final String code;
  private final String message;
}
