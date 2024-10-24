package com.project.trash.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class LoginRequest {

  @Schema(description = "회원 소셜 타입 (K - KAKAO, N - NAVER)", example = "N")
  private String socialType;

  @Schema(description = "엑세스 토큰", example = "AAAAOQyhkOCIHqxBRocc56YEJ5txff0nDZxWURIixTjytcKFsQcQgCv-y6MiC8ObOrmiJfWHMwFn-Y6JsXYm-oiMXrE")
  private String accessToken;

  @Schema(description = "소셜 ID", example = "XGJbTOt3U-Ahqghp9x61PFduxX_sGpk-M0ZdJSm5ZUc")
  private String socialId;
}
