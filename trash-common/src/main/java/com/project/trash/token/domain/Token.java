package com.project.trash.token.domain;

import com.project.trash.common.domain.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TOKEN")
public class Token extends BaseTimeEntity {

  @Id
  @Column(name = "MBR_ID", nullable = false)
  private String memberId;

  @Column(name = "TKN_ACS", nullable = false)
  private String accessToken;

  @Column(name = "TKN_RFRS", nullable = false)
  private String refreshToken;

  public Token(String memberId, String accessToken, String refreshToken) {
    this.memberId = memberId;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

  public void updateToken(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}
