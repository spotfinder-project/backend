package com.project.trash.member.sns.provider;

import com.project.trash.member.domain.enums.SocialType;

/**
 * AuthCode 요청 Url 제공
 */
public interface AuthCodeRequestUrlProvider {

  /**
   * @return 로그인 url 반환
   */
  String provideUrl();

  /**
   * @return 지원하는 SocialType
   */
  SocialType supportSocial();
}
