package com.project.trash.admin.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

/**
 * 로그인 결과 응답
 */
@Getter
@Schema(description = "로그인 결과 응답")
public class LoginResponse {

  /**
   * ID
   */
  @Schema(description = "관리자 ID", example = "testid1234")
  private final String id;

  public LoginResponse(String id) {
    this.id = id;
  }
}
