package com.project.trash.member.sns.apple;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth2.apple")
public record AppleProperties(

    String teamId,
    String keyId,
    String clientId,
    String redirectUri,
    String authorizeUri,
    String tokenUri,
    String revokeUri,
    String responseMode,
    String[] responseType,
    String[] scope,
    String keyPath) {
}
