package com.project.trash.member.sns.naver;

import com.project.trash.member.sns.client.SocialApiClient;
import com.project.trash.member.domain.OAuthMember;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.utils.LogUtils;
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
import static com.project.trash.common.domain.resultcode.SystemResultCode.SOCIAL_API_FAIL;

/**
 * Naver Api 요청
 */
@RequiredArgsConstructor
@Component
public class NaverApiClient implements SocialApiClient {

  private final NaverProperties naverProperties;

  @Override
  public String getAccessToken(String authCode) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "authorization_code");
    params.add("client_id", naverProperties.clientId());
    params.add("redirect_uri", naverProperties.redirectUri());
    params.add("code", authCode);
    params.add("client_secret", naverProperties.clientSecret());

    String resultText = WebClient.create(naverProperties.tokenUri())
                                 .post()
                                 .bodyValue(params)
                                 .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                                 .exchangeToMono(res -> res.bodyToMono(String.class))
                                 .block();

    return extractToken(resultText);
  }

  @Override
  public SocialType supportSocial() {
    return SocialType.NAVER;
  }

  @Override
  public OAuthMember getMemberInfo(String accessToken) {
    return makeOAuthMember(fetchMemberInfo(accessToken));
  }

  @Override
  public String getSocialId(String accessToken) {
    try {
      JSONObject result = new JSONObject(fetchMemberInfo(accessToken));
      String id = result.getJSONObject("response").getString("id");
      LogUtils.info("Naver getSocialId: " + id);
      return id;
    } catch (Exception e) {
      LogUtils.error("Naver getSocialId error: " + e.getMessage());
      throw new ValidationException(AUTH_OAUTH_GET_MEMBER_FAIL);
    }
  }

  @Override
  public void unlink(String accessToken) {
    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    params.add("grant_type", "delete");
    params.add("client_id", naverProperties.clientId());
    params.add("client_secret", naverProperties.clientSecret());
    params.add("access_token", accessToken);

    NaverUnlinkResponse response = WebClient.create(naverProperties.tokenUri())
                                 .post()
                                 .bodyValue(params)
                                 .exchangeToMono(res -> res.bodyToMono(NaverUnlinkResponse.class))
                                 .block();

    if (response != null && !response.getResult().equalsIgnoreCase("success")) {
      throw new ValidationException(SOCIAL_API_FAIL);
    }
  }

  private String extractToken(String resultText) {
    try {
      JSONObject jsonObject = new JSONObject(resultText);
      String accessToken = jsonObject.getString("access_token");
      LogUtils.info("Naver getAccessToken: " + accessToken);
      return accessToken;
    } catch (Exception e) {
      LogUtils.error("Naver getAccessToken error: " + e.getMessage());
      throw new ValidationException(AUTH_OAUTH_GET_ACCESS_TOKEN_FAIL);
    }
  }

  private String fetchMemberInfo(String accessToken) {
    return WebClient.create(naverProperties.userInfoUri())
                    .get()
                    .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                    .header(HttpHeaders.AUTHORIZATION, naverProperties.authorizationPrefix() + accessToken)
                    .exchangeToMono(res -> res.bodyToMono(String.class))
                    .block();
  }

  private OAuthMember makeOAuthMember(String resultText) {
    try {
      JSONObject result = new JSONObject(resultText);
      JSONObject response = result.getJSONObject("response");
      LogUtils.info("Naver getMemberInfo: " + result);
      String id = response.getString("id");
      String email = response.getString("email");
//      String gender = response.getString("gender");
//      GenderType genderType = gender.equals("U") ? GenderType.NONE : GenderType.fromCode(gender);
      return new OAuthMember(id, email, GenderType.NONE, SocialType.NAVER);
    } catch (Exception e) {
      LogUtils.error("Naver getMemberInfo error: " + e.getMessage());
      throw new ValidationException(AUTH_OAUTH_GET_MEMBER_FAIL);
    }
  }
}
