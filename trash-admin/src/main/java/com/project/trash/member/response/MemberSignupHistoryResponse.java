package com.project.trash.member.response;

import com.project.trash.common.utils.DateTimeUtils;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(title = "회원가입 이력 조회 응답")
public class MemberSignupHistoryResponse {

  @Schema(description = "날짜 (yyyy-MM-dd)", example = "2024-09-01")
  private String date;

  @Schema(description = "회원가입 수", example = "10")
  private Long count;

  public MemberSignupHistoryResponse(LocalDate createdDate, Long count) {
    this.date = DateTimeUtils.convertToString(createdDate, DateTimeUtils.DEFAULT_DATE);
    this.count = count;
  }
}