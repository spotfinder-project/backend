package com.project.trash.member.domain.enums;

import com.project.trash.common.domain.converter.AbstractEnumCodeConverter;
import com.project.trash.common.domain.enums.Codable;

import java.util.EnumSet;

import jakarta.persistence.Converter;
import lombok.Getter;

/**
 * 소셜 로그인 타입
 */
@Getter
public enum SocialType implements Codable {

  KAKAO("K"),
  NAVER("N"),
  APPLE("A");

  private final String code;

  SocialType(String code) {
    this.code = code;
  }

  public static boolean containCode(String code) {
    return EnumSet.allOf(SocialType.class).stream().anyMatch(e -> e.getCode().equals(code));
  }

  public static SocialType fromCode(String code) {
    return Codable.fromCode(SocialType.class, code);
  }

  @Converter
  public static class TypeCodeConverter extends AbstractEnumCodeConverter<SocialType> {
    @Override
    public SocialType convertToEntityAttribute(String dbData) {
      return this.toEntityAttribute(SocialType.class, dbData);
    }
  }
}
