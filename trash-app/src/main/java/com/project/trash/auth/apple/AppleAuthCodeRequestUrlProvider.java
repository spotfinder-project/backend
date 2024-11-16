package com.project.trash.auth.apple;

import com.project.trash.auth.provider.AuthCodeRequestUrlProvider;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppleAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

  private final AppleProperties appleProperties;

  @Override
  public String provideUrl() {
    return UriComponentsBuilder.fromUriString(appleProperties.authorizeUri())
                               .queryParam("response_type", String.join(" ", appleProperties.responseType()))
                               .queryParam("response_mode", appleProperties.responseMode())
                               .queryParam("client_id", appleProperties.clientId())
                               .queryParam("redirect_uri", appleProperties.redirectUri())
                               .queryParam("scope", String.join(" ", appleProperties.scope()))
                               .toUriString();
  }

  @Override
  public SocialType supportSocial() {
    return SocialType.APPLE;
  }
}
