package com.project.trash.review.response;

import com.project.trash.common.utils.DateTimeUtils;

import org.jooq.types.ULong;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import trash.tables.records.FacilityRecord;
import trash.tables.records.ReviewRecord;

/**
 * 시설물 목록 응답
 */
@Getter
@Setter
@Schema(title = "시설물 목록 조회 응답")
public class ReviewListResponse {

  @Schema(description = "리뷰 ID", example = "1")
  private ULong reviewId;

  @Schema(description = "회원 닉네임", example = "Kim")
  private String nickname;

  @Schema(description = "리뷰 내용", example = "시설물이 청결합니다~")
  private String content;

  @Schema(description = "리뷰 등록일자", example = "2024-09-01")
  private String createdDate;

  private FacilityInfo facility;

  public ReviewListResponse(ReviewRecord review, FacilityRecord facility, String nickname) {
    this.reviewId = review.getRvwId();
    this.nickname = nickname;
    this.content = review.getRvwCtt();
    this.facility = new FacilityInfo(facility);
    this.createdDate = DateTimeUtils.convertToString(review.getCreDtm(), DateTimeUtils.DEFAULT_DATE);
  }

  @Getter
  @Setter
  public static class FacilityInfo {

    @Schema(description = "시설물 ID", example = "1")
    private ULong facilityId;

    @Schema(description = "시설물 종류 (R - 화장실, S - 흡연구역, T - 쓰레기통)", example = "R")
    private String type;

    @Schema(description = "시설물명", example = "쌍문역 내 화장실")
    private String name;

    @Schema(description = "위치", example = "쌍문역")
    private String location;

    public FacilityInfo(FacilityRecord facility) {
      this.facilityId = facility.getFcltyId();
      this.type = facility.getFcltyTyp();
      this.name = facility.getFcltyNm();
      this.location = facility.getFcltyLctn();
    }
  }
}
