package com.project.trash.report.request;

import com.project.trash.common.request.PageRequest;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

/**
 * 신고 목록 조회 요청
 */
@Getter
@Setter
public class ReportListRequest extends PageRequest {

  /**
   * 신고 내용
   */
  @Parameter(description = "신고 내용", example = "청소")
  private String content;
  /**
   * 상태
   */
  @Parameter(description = "신고 처리상태(Y - 처리완료, N - 미처리)", example = "Y")
  private String status;
  /**
   * 회원 ID(신고자 ID)
   */
  @Parameter(description = "회원 ID", example = "1")
  private Long memberId;
  /**
   * 시설물 ID
   */
  @Parameter(description = "시설물 ID", example = "1")
  private Long facilityId;
  /**
   * 생성일 검색 시작일
   */
  @Parameter(description = "신고 생성일 검색 시작일 (yyyy-MM-dd)", example = "2024-06-01")
  private String startDate;
  /**
   * 생성일 검색 종료일
   */
  @Parameter(description = "신고 생성일 검색 종료일 (yyyy-MM-dd)", example = "2024-09-08")
  private String endDate;
}
