package com.project.trash.facility.request;

import com.project.trash.common.request.PageRequest;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityListRequest extends PageRequest {

  @Parameter(description = "시설물 ID", example = "1")
  private Long facilityId;

  @Parameter(description = "시설물 종류 (R - 화장실, S - 흡연구역, T - 쓰레기통)", example = "R")
  private String type;

  @Parameter(description = "시설물명", example = "쌍문역 내 화장실")
  private String name;

  @Parameter(description = "위치", example = "쌍문역")
  private String location;

  @Parameter(description = "승인 상태(P - 승인대기, A - 승인완료, R - 승인거절, S - 승인중단)", example = "A")
  private String approvalStatus;

  @Parameter(description = "시설물 생성일 검색 시작일 (yyyy-MM-dd)", example = "2024-06-01")
  private String startDate;

  @Parameter(description = "시설물 생성일 검색 종료일 (yyyy-MM-dd)", example = "2024-09-08")
  private String endDate;
}
