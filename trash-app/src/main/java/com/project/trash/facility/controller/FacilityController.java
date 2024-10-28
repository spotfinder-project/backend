package com.project.trash.facility.controller;

import com.project.trash.common.request.PageRequest;
import com.project.trash.common.response.DataResponse;
import com.project.trash.common.response.ImageEntryResponse;
import com.project.trash.common.response.PageListResponse;
import com.project.trash.common.response.SuccessResponse;
import com.project.trash.facility.controller.validation.FacilityValidator;
import com.project.trash.facility.request.FacilityEntryRequest;
import com.project.trash.facility.request.FacilityListRequest;
import com.project.trash.facility.request.FacilityModifyRequest;
import com.project.trash.facility.request.ReportEntryRequest;
import com.project.trash.facility.request.ReviewEntryRequest;
import com.project.trash.facility.request.ReviewModifyRequest;
import com.project.trash.facility.response.FacilityDetailResponse;
import com.project.trash.facility.response.FacilityListResponse;
import com.project.trash.facility.response.FacilityReviewListResponse;
import com.project.trash.facility.service.FacilityCommandService;
import com.project.trash.facility.service.FacilityQueryService;
import com.project.trash.facility.service.ReportCommandService;
import com.project.trash.facility.service.ReviewCommandService;
import com.project.trash.facility.service.ReviewQueryService;

import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/facilities")
@RequiredArgsConstructor
@Tag(name = "시설물")
public class FacilityController {

  private final FacilityCommandService facilityCommandService;
  private final FacilityQueryService facilityQueryService;

  private final ReviewQueryService reviewQueryService;
  private final ReviewCommandService reviewCommandService;

  private final ReportCommandService reportCommandService;

  @DeleteMapping("/{facilityId}")
  @Operation(summary = "시설물 삭제",
      description = "시설물을 삭제한다."
          + "\n[에러 코드]"
          + "\n- FAC000 : 시설물 정보가 존재하지 않습니다.")
  public SuccessResponse delete(
      @Parameter(description = "삭제할 시설물의 ID", required = true, example = "1") @PathVariable Long facilityId) {

    facilityCommandService.delete(facilityId);
    return new SuccessResponse();
  }

  @DeleteMapping("/reviews/{reviewId}")
  @Operation(summary = "리뷰 삭제",
      description = "리뷰를 삭제한다."
          + "\n[에러 코드]"
          + "\n- RVW000 : 리뷰 정보가 존재하지 않습니다.")
  public SuccessResponse deleteReview(
      @Parameter(description = "삭제할 리뷰의 ID", required = true, example = "1") @PathVariable Long reviewId) {
    reviewCommandService.delete(reviewId);
    return new SuccessResponse();
  }

  @GetMapping("/{facilityId}")
  @Operation(summary = "시설물 상세 조회",
      description = "시설물 정보를 상세 조회한다."
          + "\n[에러 코드]"
          + "\n- FAC000 : 시설물 정보가 존재하지 않습니다.")
  public DataResponse<FacilityDetailResponse> getDetail(
      @Parameter(description = "조회할 시설물의 ID", required = true, example = "1") @PathVariable Long facilityId) {

    return new DataResponse<>(facilityQueryService.getDetail(facilityId));
  }

  @GetMapping
  @Operation(summary = "시설물 목록 조회",
      description = "시설물 목록을 조회한다.")
  public DataResponse<FacilityListResponse> getList(@ParameterObject FacilityListRequest param) {
    FacilityValidator.validate(param);

    return new DataResponse<>(facilityQueryService.getList(param));
  }

  @GetMapping("/{facilityId}/reviews")
  @Operation(summary = "시설물 리뷰 목록 조회",
      description = "시설물의 리뷰 목록을 조회한다.")
  public PageListResponse<FacilityReviewListResponse> getReviewList(
      @Parameter(description = "조회할 시설물의 ID", required = true, example = "1") @PathVariable Long facilityId,
      @ParameterObject PageRequest param) {

    Pair<List<FacilityReviewListResponse>, Long> pair = reviewQueryService.getList(facilityId, param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  @GetMapping("/{facilityId}/reviews/count")
  @Operation(summary = "시설물 리뷰 개수 조회",
      description = "시설물에 등록된 리뷰 개수를 조회한다.")
  public DataResponse<Long> getReviewCount(
      @Parameter(description = "조회할 시설물의 ID", required = true, example = "1") @PathVariable Long facilityId) {

    return new DataResponse<>(reviewQueryService.getCount(facilityId));
  }

  @PostMapping
  @Operation(summary = "시설물 등록",
      description = "시설물을 등록한다.")
  public DataResponse<Long> post(@RequestBody FacilityEntryRequest param) {
    FacilityValidator.validate(param);

    return new DataResponse<>(facilityCommandService.entry(param));
  }

  @PostMapping(value = "/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(summary = "시설물 이미지 등록",
      description = "시설물 이미지를 등록한다.")
  public DataResponse<ImageEntryResponse> postImage(
      @Parameter(
          description = "이미지 목록",
          content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)
      )
      @RequestPart(required = false) List<MultipartFile> images) {
    FacilityValidator.validate(images);

    return new DataResponse<>(facilityCommandService.entry(images));
  }

  @PostMapping("/reports")
  @Operation(summary = "신고 등록",
      description = "신고를 등록한다.")
  public SuccessResponse postReport(@RequestBody ReportEntryRequest param) {
    FacilityValidator.validate(param);

    reportCommandService.entry(param);
    return new SuccessResponse();
  }

  @PostMapping("/reviews")
  @Operation(summary = "리뷰 등록",
      description = "리뷰를 등록한다.")
  public SuccessResponse postReview(@RequestBody ReviewEntryRequest param) {
    FacilityValidator.validate(param);

    reviewCommandService.entry(param);
    return new SuccessResponse();
  }

  @PutMapping
  @Operation(summary = "시설물 수정",
      description = "시설물을 수정한다."
          + "\n[에러 코드]"
          + "\n- FAC000 : 시설물 정보가 존재하지 않습니다.")
  public DataResponse<Long> put(@RequestBody FacilityModifyRequest param) {
    FacilityValidator.validate(param);

    return new DataResponse<>(facilityCommandService.modify(param));
  }

  @PutMapping("/reviews")
  @Operation(summary = "리뷰 수정",
      description = "리뷰를 수정한다."
          + "\n[에러 코드]"
          + "\n- FAC000 : 시설물 정보가 존재하지 않습니다."
          + "\n- RVW000 : 리뷰 정보가 존재하지 않습니다.")
  public SuccessResponse putReview(@RequestBody ReviewModifyRequest param) {
    FacilityValidator.validate(param);

    reviewCommandService.modify(param);
    return new SuccessResponse();
  }
}
