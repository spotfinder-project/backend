package com.project.trash.member.sns.apple;

import com.project.trash.member.sns.client.SocialApiClient;
import com.project.trash.member.domain.OAuthMember;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AppleApiClient implements SocialApiClient {

  private final AppleService appleService;

  @Override
  public OAuthMember getMemberInfo(String accessToken) {
    return appleService.getMemberInfo(accessToken);
  }

  @Override
  public String getAccessToken(String authCode) {
    return "";
  }

  @Override
  public String getSocialId(String accessToken) {
    return "";
  }

  @Override
  public SocialType supportSocial() {
    return SocialType.APPLE;
  }

  @Override
  public void unlink(String accessToken) {

  }
}
