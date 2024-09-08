package com.project.trash.member.controller;

import com.project.trash.common.response.DataResponse;
import com.project.trash.common.response.PageListResponse;
import com.project.trash.common.response.SuccessResponse;
import com.project.trash.facility.service.FacilityQueryService;
import com.project.trash.member.controller.validation.MemberValidator;
import com.project.trash.member.request.MemberFacilityListRequest;
import com.project.trash.member.request.MemberListRequest;
import com.project.trash.member.request.MemberReviewListRequest;
import com.project.trash.member.response.MemberDetailResponse;
import com.project.trash.member.response.MemberFacilityListResponse;
import com.project.trash.member.response.MemberListResponse;
import com.project.trash.member.response.MemberReviewListResponse;
import com.project.trash.member.service.MemberCommandService;
import com.project.trash.member.service.MemberQueryService;
import com.project.trash.review.service.ReviewCommandService;
import com.project.trash.review.service.ReviewQueryService;

import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * 회원 API
 */
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "회원")
public class MemberController {

  private final MemberQueryService memberQueryService;
  private final MemberCommandService memberCommandService;

  private final FacilityQueryService facilityQueryService;

  private final ReviewQueryService reviewQueryService;
  private final ReviewCommandService reviewCommandService;

  /**
   * 회원 삭제
   */
  @DeleteMapping("/{memberSeq}")
  @Operation(summary = "회원 삭제",
      description = "회원을 삭제한다."
          + "\n[에러 코드]"
          + "\n- MBR000 : 회원 정보가 존재하지 않습니다.")
  public SuccessResponse delete(
      @Parameter(description = "삭제할 리뷰의 일련번호", required = true, example = "1") @PathVariable Long memberSeq) {

    memberCommandService.delete(memberSeq);
    return new SuccessResponse();
  }

  /**
   * 리뷰 삭제
   */
  @DeleteMapping("/reviews/{reviewSeq}")
  @Operation(summary = "리뷰 삭제",
      description = "리뷰를 삭제한다."
          + "\n[에러 코드]"
          + "\n- RVW000 : 리뷰 정보가 존재하지 않습니다.")
  public SuccessResponse deleteReview(
      @Parameter(description = "삭제할 리뷰의 일련번호", required = true, example = "1") @PathVariable Long reviewSeq) {

    reviewCommandService.delete(reviewSeq);
    return new SuccessResponse();
  }

  /**
   * 회원 상세 조회
   */
  @GetMapping("/{memberSeq}")
  @Operation(summary = "회원 상세 조회",
      description = "회원 정보를 상세 조회한다."
          + "\n[에러 코드]"
          + "\n- MBR000 : 회원 정보가 존재하지 않습니다.")
  public DataResponse<MemberDetailResponse> getDetail(
      @Parameter(description = "조회할 회원의 일련번호", required = true, example = "1") @PathVariable Long memberSeq) {

    return new DataResponse<>(memberQueryService.getDetail(memberSeq));
  }

  /**
   * 등록한 시설물 목록 조회
   */
  @GetMapping("/facilities")
  @Operation(summary = "회원이 등록한 시설물 목록 조회",
      description = "회원이 등록한 시설물 목록을 조회한다."
          + "\n[에러 코드]"
          + "\n- MBR000 : 회원 정보가 존재하지 않습니다.")
  public PageListResponse<MemberFacilityListResponse> getFacilityList(@ParameterObject MemberFacilityListRequest param) {
    MemberValidator.validate(param);

    Pair<List<MemberFacilityListResponse>, Long> pair = facilityQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  /**
   * 회원 목록 조회
   */
  @GetMapping
  @Operation(summary = "회원 목록 조회",
      description = "회원 목록을 조회한다.")
  public PageListResponse<MemberListResponse> getList(@ParameterObject MemberListRequest param) {
    MemberValidator.validate(param);

    Pair<List<MemberListResponse>, Long> pair = memberQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  /**
   * 등록한 리뷰 목록 조회
   */
  @GetMapping("/reviews")
  @Operation(summary = "회원이 등록한 리뷰 목록 조회",
      description = "회원이 등록한 리뷰 목록을 조회한다.")
  public PageListResponse<MemberReviewListResponse> getReviewList(@ParameterObject MemberReviewListRequest param) {
    MemberValidator.validate(param);

    Pair<List<MemberReviewListResponse>, Long> pair = reviewQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }
}
