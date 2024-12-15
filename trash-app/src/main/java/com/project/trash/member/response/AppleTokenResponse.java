package com.project.trash.member.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class AppleTokenResponse {

  @JsonProperty("access_token")
  private String accessToken;
  @JsonProperty("expires_in")
  private String expiresIn;
  @JsonProperty("id_token")
  private String idToken;
  @JsonProperty("refresh_token")
  private String refreshToken;
  @JsonProperty("token_type")
  private String tokenType;
  private String error;
}
