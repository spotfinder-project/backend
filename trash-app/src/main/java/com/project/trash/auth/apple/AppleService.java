package com.project.trash.auth.apple;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.project.trash.auth.domain.OAuthMember;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.utils.LogUtils;
import com.project.trash.member.domain.enums.GenderType;
import com.project.trash.member.domain.enums.SocialType;
import com.project.trash.member.response.AppleTokenResponse;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.KeyFactory;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

import javax.management.openmbean.InvalidKeyException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_APPLE_REVOKE_FAIL;
import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_OAUTH_GET_MEMBER_FAIL;

@Service
@RequiredArgsConstructor
public class AppleService {

  private final String APPLE_AUTH_URL = "https://appleid.apple.com";

  private final AppleProperties appleProperties;

  public OAuthMember getMemberInfo(String code) {
    return makeOAuthMember(getToken(code));
  }

  public void revoke(String code) {
    String refreshToken = getToken(code).getRefreshToken();

    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("client_id", appleProperties.clientId());
      params.add("token", refreshToken);
      params.add("client_secret", createClientSecret());
      params.add("token_type_hint", "refresh_token");

      WebClient.create(appleProperties.revokeUri())
                      .post()
                      .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                      .bodyValue(params)
                      .exchangeToMono(res -> Mono.empty())
                      .block();
    } catch (Exception e) {
      throw new ValidationException(AUTH_APPLE_REVOKE_FAIL, e);
    }
  }

  private AppleTokenResponse getToken(String code) {
    try {
      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
      params.add("grant_type", "authorization_code");
      params.add("client_id", appleProperties.clientId());
      params.add("redirect_uri", appleProperties.redirectUri());
      params.add("code", code);
      params.add("client_secret", createClientSecret());

      return WebClient.create(appleProperties.tokenUri())
                      .post()
                      .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                      .bodyValue(params)
                      .exchangeToMono(res -> res.bodyToMono(AppleTokenResponse.class))
                      .block();
    } catch (Exception e) {
      throw new ValidationException(AUTH_OAUTH_GET_MEMBER_FAIL, e);
    }
  }

  private String createClientSecret() throws Exception {
    JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.ES256).keyID(appleProperties.keyId()).build();
    Date now = new Date();

    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
        .issuer(appleProperties.teamId())
        .issueTime(now)
        .expirationTime(new Date(now.getTime() + 3600000))
        .audience(APPLE_AUTH_URL)
        .subject(appleProperties.clientId())
        .build();

    SignedJWT jwt = new SignedJWT(header, claimsSet);

    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(getPrivateKey());
    KeyFactory kf = KeyFactory.getInstance("EC");
    try {
      ECPrivateKey ecPrivateKey = (ECPrivateKey) kf.generatePrivate(spec);

      JWSSigner jwsSigner = new ECDSASigner(ecPrivateKey);
      jwt.sign(jwsSigner);

    } catch (InvalidKeyException | JOSEException e) {
      throw new Exception("Failed create client secret");
    }

    return jwt.serialize();
  }

  private byte[] getPrivateKey() {
    byte[] content = null;
    File file = null;

    URL res = getClass().getResource(appleProperties.keyPath());

    if ("jar".equals(res.getProtocol())) {
      try {
        InputStream input = getClass().getResourceAsStream(appleProperties.keyPath());
        file = File.createTempFile("tempfile", ".tmp");
        OutputStream out = new FileOutputStream(file);

        int read;
        byte[] bytes = new byte[1024];

        while ((read = input.read(bytes)) != -1) {
          out.write(bytes, 0, read);
        }

        out.close();
        file.deleteOnExit();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else {
      file = new File(res.getFile());
    }

    if (file.exists()) {
      try (FileReader keyReader = new FileReader(file);
           PemReader pemReader = new PemReader(keyReader)) {
        PemObject pemObject = pemReader.readPemObject();
        content = pemObject.getContent();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      LogUtils.error("File " + file + " not found");
      throw new ValidationException(AUTH_OAUTH_GET_MEMBER_FAIL);
    }

    return content;
  }

  private OAuthMember makeOAuthMember(AppleTokenResponse token) {
    try {
      //ID TOKEN을 통해 회원 고유 식별자 받기
      SignedJWT signedJWT = SignedJWT.parse(token.getIdToken());
      JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();

      String userId = claimsSet.getStringClaim("sub"); //
      String email = claimsSet.getStringClaim("email");

      return new OAuthMember(userId, email, GenderType.NONE, SocialType.APPLE);
    } catch (Exception e) {
      throw new ValidationException(AUTH_OAUTH_GET_MEMBER_FAIL, e);
    }
  }
}
