package com.project.trash.auth.kakao;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.kakao")
public record KakaoProperties(

    String redirectUri,
    String clientId,
    String clientSecret,
    String[] scope,
    String authorizeUri,
    String tokenUri,
    String tokenInfoUri,
    String userInfoUri,
    String unlinkUri,
    String authorizationPrefix) {
}
