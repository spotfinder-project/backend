package com.project.trash.member.sns.provider;

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
 * AuthCode 요청 Url 제공
 */
@Component
public class AuthCodeRequestUrlProviderComposite {

  private final Map<SocialType, AuthCodeRequestUrlProvider> providerMap;

  public AuthCodeRequestUrlProviderComposite(Set<AuthCodeRequestUrlProvider> providers) {
    providerMap = providers.stream().collect(toMap(AuthCodeRequestUrlProvider::supportSocial, identity()));
  }

  /**
   * 소셜 타입에 맞는 url 반환
   *
   * @param socialType 소셜 타입
   * @return authcode 요청 url
   */
  public String provide(SocialType socialType) {
    return getProvider(socialType).provideUrl();
  }

  private AuthCodeRequestUrlProvider getProvider(SocialType socialType) {
    return Optional.ofNullable(providerMap.get(socialType)).orElseThrow(() -> new ValidationException(PARAM_INVALID));
  }
}
