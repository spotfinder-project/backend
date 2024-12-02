package com.project.trash.review.request;

import com.project.trash.common.request.PageRequest;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewListRequest extends PageRequest {

  @Parameter(description = "회원 닉네임", example = "Kim")
  private String nickname;

  @Parameter(description = "시설물명", example = "화장실")
  private String facilityName;

  @Parameter(description = "리뷰 내용", example = "시설물이 청결합니다~")
  private String content;

  @Parameter(description = "리뷰 생성일 검색 시작일 (yyyy-MM-dd)", example = "2024-06-01")
  private String startDate;

  @Parameter(description = "리뷰 생성일 검색 종료일 (yyyy-MM-dd)", example = "2024-09-08")
  private String endDate;
}
