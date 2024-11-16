package com.project.trash.auth.kakao;

import com.project.trash.auth.client.SocialApiClient;
import com.project.trash.auth.domain.OAuthMember;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.member.domain.enums.GenderType;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_OAUTH_GET_ACCESS_TOKEN_FAIL;
import static com.project.trash.common.domain.resultcode.AuthResultCode.AUTH_OAUTH_GET_MEMBER_FAIL;

/**
 * Kakao Api 요청
 */
@RequiredArgsConstructor
@Component
public class KakaoApiClient implements SocialApiClient {

  private final KakaoProperties kakaoProperties;

  @Override
  public OAuthMember getMemberInfo(String accessToken) {
    String resultText = WebClient.create(kakaoProperties.userInfoUri())
                                 .get()
                                 .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                                 .header(HttpHeaders.AUTHORIZATION,
                                     kakaoProperties.authorizationPrefix() + accessToken)
                                 .exchangeToMono(res -> res.bodyToMono(String.class))
                                 .block();

    return makeOAuthMember(resultText);
  }

  @Override
  public String getAccessToken(String authCode) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", kakaoProperties.clientId());
    params.add("redirect_uri", kakaoProperties.redirectUri());
    params.add("code", authCode);
    params.add("client_secret", kakaoProperties.clientSecret());

    String resultText = WebClient.create(kakaoProperties.tokenUri())
                                 .post()
                                 .bodyValue(params)
                                 .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                                 .exchangeToMono(res -> res.bodyToMono(String.class))
                                 .block();

    return extractToken(resultText);
  }

  @Override
  public String getSocialId(String accessToken) {
    String resultText = WebClient.create(kakaoProperties.tokenInfoUri())
                                 .get()
                                 .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                                 .header(HttpHeaders.AUTHORIZATION,
                                     kakaoProperties.authorizationPrefix() + accessToken)
                                 .exchangeToMono(res -> res.bodyToMono(String.class))
                                 .block();

    return extractSocialId(resultText);
  }

  @Override
  public SocialType supportSocial() {
    return SocialType.KAKAO;
  }

  @Override
  public void unlink(String accessToken) {
    WebClient.create(kakaoProperties.unlinkUri())
             .post()
             .header(HttpHeaders.AUTHORIZATION,
                 kakaoProperties.authorizationPrefix() + accessToken)
             .exchangeToMono(res -> res.bodyToMono(String.class))
             .block();
  }

  /**
   * 소셜 ID 추출
   */
  private String extractSocialId(String resultText) {
    try {
      JSONObject jsonObject = new JSONObject(resultText);
      return String.valueOf(jsonObject.getLong("id"));
    } catch (Exception e) {
      throw new ValidationException(AUTH_OAUTH_GET_ACCESS_TOKEN_FAIL);
    }
  }

  private String extractToken(String resultText) {
    try {
      JSONObject jsonObject = new JSONObject(resultText);
      return jsonObject.getString("access_token");
    } catch (Exception e) {
      throw new ValidationException(AUTH_OAUTH_GET_ACCESS_TOKEN_FAIL);
    }
  }

  private OAuthMember makeOAuthMember(String resultText) {
    try {
      JSONObject result = new JSONObject(resultText);
      Long id = result.getLong("id");
      JSONObject kakaoAccount = result.getJSONObject("kakao_account");
      String email = kakaoAccount.getString("email");
      //      GenderType gender = kakaoAccount.getString("gender").equals("male") ? GenderType.MALE : GenderType.FEMALE;
      return new OAuthMember(id.toString(), email, GenderType.MALE, SocialType.KAKAO);
    } catch (Exception e) {
      throw new ValidationException(AUTH_OAUTH_GET_MEMBER_FAIL);
    }
  }
}
