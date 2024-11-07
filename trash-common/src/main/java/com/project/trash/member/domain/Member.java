package com.project.trash.member.domain;

import com.project.trash.common.domain.BaseTimeEntity;
import com.project.trash.member.domain.enums.GenderType;
import com.project.trash.member.domain.enums.SocialType;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원 엔티티
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member extends BaseTimeEntity {

  @Id
  @Column(name = "MBR_ID", nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long memberId;

  @Column(name = "MBR_EMAIL", nullable = false)
  private String email;

  @Column(name = "MBR_NCK_NM", nullable = false)
  private String nickname = "";

  @Convert(converter = GenderType.TypeCodeConverter.class)
  @Column(name = "MBR_GNDR")
  private GenderType gender;

  @Column(name = "MBR_SCL_ID", nullable = false)
  private String socialId;

  @Convert(converter = SocialType.TypeCodeConverter.class)
  @Column(name = "MBR_SCL_TYP", nullable = false)
  private SocialType socialType;

  @Column(name = "MBR_ARGM_YN", nullable = false)
  private Boolean agreementYn = Boolean.FALSE;

  @Column(name = "MBR_VLD_YN", nullable = false)
  private Boolean valid = Boolean.TRUE;

  public Member(String email, GenderType gender, String socialId, SocialType socialType) {
    this.email = email;
    this.gender = gender;
    this.socialId = socialId;
    this.socialType = socialType;
  }

  public void update(String nickname) {
    this.nickname = nickname;
  }

  public void delete() {
    this.valid = Boolean.FALSE;
    this.email = "null";
    this.socialId = "null";
    this.nickname = "알 수 없음";
  }
}
