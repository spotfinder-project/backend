package com.project.trash.member.sns.naver;

import com.project.trash.member.sns.provider.AuthCodeRequestUrlProvider;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

/**
 * Naver AuthCode 요청 Url 제공
 */
@Component
@RequiredArgsConstructor
public class NaverAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

  private final NaverProperties naverProperties;

  @Override
  public String provideUrl() {
    return UriComponentsBuilder.fromUriString(naverProperties.authorizeUri())
                               .queryParam("response_type", "code")
                               .queryParam("client_id", naverProperties.clientId())
                               .queryParam("redirect_uri", naverProperties.redirectUri())
                               .queryParam("scope", String.join(",", naverProperties.scope()))
                               .toUriString();
  }

  @Override
  public SocialType supportSocial() {
    return SocialType.NAVER;
  }
}
