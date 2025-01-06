package com.project.trash.member.controller;

import com.project.trash.common.response.DataResponse;
import com.project.trash.common.response.ListResponse;
import com.project.trash.common.response.PageListResponse;
import com.project.trash.common.response.SuccessResponse;
import com.project.trash.common.utils.ValidatorUtils;
import com.project.trash.facility.service.FacilityQueryService;
import com.project.trash.member.controller.validation.MemberValidator;
import com.project.trash.member.request.*;
import com.project.trash.member.response.MemberDetailResponse;
import com.project.trash.member.response.MemberFacilityListResponse;
import com.project.trash.member.response.MemberListResponse;
import com.project.trash.member.response.MemberReviewListResponse;
import com.project.trash.member.response.MemberSignupHistoryResponse;
import com.project.trash.member.service.MemberCommandService;
import com.project.trash.member.service.MemberQueryService;
import com.project.trash.review.service.ReviewCommandService;
import com.project.trash.review.service.ReviewQueryService;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

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

  @PostMapping("/login")
  @Operation(summary = "로그인")
  public SuccessResponse postLogin(@RequestBody MemberLoginRequest param, HttpServletResponse response) {
    MemberValidator.validate(param);

    memberCommandService.login(param, response);
    return new SuccessResponse();
  }

  @DeleteMapping("/{memberId}")
  @Operation(summary = "회원 삭제",
      description = "회원을 삭제한다."
          + "\n[에러 코드]"
          + "\n- MBR000 : 회원 정보가 존재하지 않습니다.")
  public SuccessResponse delete(
      @Parameter(description = "삭제할 회원의 ID", required = true, example = "1") @PathVariable Long memberId) {

    memberCommandService.delete(memberId);
    return new SuccessResponse();
  }

  @PostMapping("/unlink")
  @Operation(summary = "소셜 로그인 연동 해제")
  public SuccessResponse unlink(@RequestBody MemberUnlinkRequest param) {
    ValidatorUtils.validateEmpty(param.getAuthCode());

    memberCommandService.unlink(param.getAuthCode());
    return new SuccessResponse();
  }

  @DeleteMapping("/reviews")
  @Operation(summary = "선택한 리뷰 목록 삭제",
      description = "선택한 리뷰들을 삭제한다."
          + "\n[에러 코드]"
          + "\n- RVW000 : 리뷰 정보가 존재하지 않습니다.")
  public SuccessResponse deleteReview(
      @Parameter(description = "삭제할 리뷰들의 ID 목록", required = true, example = "[1, 2, 3]") @RequestParam
      Set<Long> reviewIds) {

    reviewCommandService.delete(reviewIds);
    return new SuccessResponse();
  }

  @PutMapping("/reviews")
  @Operation(summary = "회원 리뷰 수정",
      description = "회원의 리뷰를 수정한다."
          + "\n[에러 코드]"
          + "\n- RVW000 : 리뷰 정보가 존재하지 않습니다.")
  public SuccessResponse putReview(@RequestBody MemberReviewModifyRequest param) {
    MemberValidator.validate(param);

    reviewCommandService.modify(param);
    return new SuccessResponse();
  }

  @GetMapping("/{memberId}")
  @Operation(summary = "회원 상세 조회",
      description = "회원 정보를 상세 조회한다."
          + "\n[에러 코드]"
          + "\n- MBR000 : 회원 정보가 존재하지 않습니다.")
  public DataResponse<MemberDetailResponse> getDetail(
      @Parameter(description = "조회할 회원의 ID", required = true, example = "1") @PathVariable Long memberId) {

    return new DataResponse<>(memberQueryService.getDetail(memberId));
  }

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

  @GetMapping
  @Operation(summary = "회원 목록 조회",
      description = "회원 목록을 조회한다.")
  public PageListResponse<MemberListResponse> getList(@ParameterObject MemberListRequest param) {
    MemberValidator.validate(param);
    Pair<List<MemberListResponse>, Long> pair = memberQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  @GetMapping("/reviews")
  @Operation(summary = "회원이 등록한 리뷰 목록 조회",
      description = "회원이 등록한 리뷰 목록을 조회한다.")
  public PageListResponse<MemberReviewListResponse> getReviewList(@ParameterObject MemberReviewListRequest param) {
    MemberValidator.validate(param);

    Pair<List<MemberReviewListResponse>, Long> pair = reviewQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  @GetMapping("/signup/history")
  @Operation(summary = "회원가입 이력 조회",
      description = "회원가입 이력을 조회한다.")
  public ListResponse<MemberSignupHistoryResponse> getSignupHistoryList(@ParameterObject MemberSignupHistoryRequest param) {
    MemberValidator.validate(param);

    return new ListResponse<>(memberQueryService.getList(param));
  }
}
