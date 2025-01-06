package com.project.trash.member.sns.kakao;

import com.project.trash.member.sns.provider.AuthCodeRequestUrlProvider;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

/**
 * 카카오 AuthCode 요청 Url 제공
 */
@Component
@RequiredArgsConstructor
public class KakaoAuthCodeRequestUrlProvider implements AuthCodeRequestUrlProvider {

  private final KakaoProperties kakaoProperties;

  @Override
  public String provideUrl() {
    return UriComponentsBuilder.fromUriString(kakaoProperties.authorizeUri())
                               .queryParam("response_type", "code")
                               .queryParam("client_id", kakaoProperties.clientId())
                               .queryParam("redirect_uri", kakaoProperties.redirectUri())
                               .queryParam("scope", String.join(",", kakaoProperties.scope()))
                               .toUriString();
  }

  @Override
  public SocialType supportSocial() {
    return SocialType.KAKAO;
  }
}
