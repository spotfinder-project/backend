package com.project.trash.member.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(title = "토큰 재발급 응답")
public class ReissueTokenResponse {

  @Schema(description = "엑세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9")
  private final String accessToken;

  @Schema(description = "엑세스 토큰 유효시간", example = "86400000")
  private final Long accessExpiration;

  @Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzUxMiJ9")
  private final String refreshToken;

  @Schema(description = "엑세스 토큰 유효시간", example = "604800000")
  private final Long refreshExpiration;
}
