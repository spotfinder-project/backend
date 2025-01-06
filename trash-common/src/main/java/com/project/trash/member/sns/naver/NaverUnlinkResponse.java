package com.project.trash.member.sns.naver;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class NaverUnlinkResponse {

  @JsonProperty("access_token")
  private final String accessToken;

  private final String result;

  public NaverUnlinkResponse(String accessToken, String result) {
    this.accessToken = accessToken;
    this.result = result;
  }
}
