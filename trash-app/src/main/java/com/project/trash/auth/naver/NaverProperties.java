package com.project.trash.auth.naver;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.naver")
public record NaverProperties(

    String redirectUri,
    String clientId,
    String clientSecret,
    String[] scope,
    String authorizeUri,
    String tokenUri,
    String userInfoUri,
    String authorizationPrefix) {
}
