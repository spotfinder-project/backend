package com.project.trash.member.request;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupHistoryRequest {

  @Parameter(description = "회원가입일 검색 시작일 (yyyy-MM-dd)", example = "2024-06-01")
  private String startDate;

  @Parameter(description = "회원가입일 검색 종료일 (yyyy-MM-dd)", example = "2024-09-08")
  private String endDate;
}
