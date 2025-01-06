package com.project.trash.member.sns.client;

import com.project.trash.member.domain.OAuthMember;
import com.project.trash.member.domain.enums.SocialType;

/**
 * Resource Server 정보 요청
 */
public interface SocialApiClient {

  /**
   * 엑세스 토큰 발급
   */
  String getAccessToken(String authCode);

  /**
   * 사용자 정보 조회
   */
  OAuthMember getMemberInfo(String accessToken);

  /**
   * 소셜 ID 조회
   */
  String getSocialId(String accessToken);

  /**
   * 지원하는 SocialType
   */
  SocialType supportSocial();

  /**
   * 연결 해제
   */
  void unlink(String accessToken);
}
