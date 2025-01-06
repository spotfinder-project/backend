package com.project.trash.member.sns.client;

import com.project.trash.member.domain.OAuthMember;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.project.trash.common.domain.resultcode.RequestResultCode.PARAM_INVALID;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * Resource Server 회원 정보 요청 카카오에서 사용자 정보를 받아오는 클래스 Composite 패턴으로 구현
 */
@Component
public class SocialApiClientComposite {

  private final Map<SocialType, SocialApiClient> clientMap;

  public SocialApiClientComposite(Set<SocialApiClient> clients) {
    clientMap = clients.stream().collect(toMap(SocialApiClient::supportSocial, identity()));
  }

  /**
   * 소셜 타입에 맞는 엑세스 토큰 발급
   */
  public String getAccessToken(SocialType socialType, String authCode) {
    return getClient(socialType).getAccessToken(authCode);
  }

  /**
   * 소셜 타입에 맞는 사용자 정보 가져오기
   */
  public OAuthMember getMemberInfo(SocialType socialType, String accessToken) {
    return getClient(socialType).getMemberInfo(accessToken);
  }

  /**
   * 엑세스 토큰 정보 확인(검증)
   */
  public String getSocialId(SocialType socialType, String accessToken) {
    return getClient(socialType).getSocialId(accessToken);
  }

  public void unlink(SocialType socialType, String accessToken) {
    getClient(socialType).unlink(accessToken);
  }

  private SocialApiClient getClient(SocialType socialType) {
    return Optional.ofNullable(clientMap.get(socialType)).orElseThrow(() -> new ValidationException(PARAM_INVALID));
  }
}
