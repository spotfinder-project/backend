package com.project.trash.review.controller;

import com.project.trash.common.response.PageListResponse;
import com.project.trash.common.response.SuccessResponse;
import com.project.trash.review.controller.validation.ReviewValidator;
import com.project.trash.review.request.ReviewListRequest;
import com.project.trash.review.response.ReviewListResponse;
import com.project.trash.review.service.ReviewCommandService;
import com.project.trash.review.service.ReviewQueryService;

import org.apache.commons.lang3.tuple.Pair;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Tag(name = "리뷰")
public class ReviewController {

  private final ReviewQueryService reviewQueryService;
  private final ReviewCommandService reviewCommandService;

  @GetMapping
  @Operation(summary = "리뷰 목록 조회", description = "리뷰 목록을 조회한다.")
  public PageListResponse<ReviewListResponse> getList(@ParameterObject ReviewListRequest param) {
    ReviewValidator.validate(param);

    Pair<List<ReviewListResponse>, Long> pair = reviewQueryService.getList(param);
    return new PageListResponse<>(param, pair.getLeft(), pair.getRight());
  }

  @DeleteMapping
  @Operation(summary = "선택한 리뷰 목록 삭제", description = "선택한 리뷰들을 삭제한다."
      + "\n[에러 코드]"
      + "\n- RVW000 : 리뷰 정보가 존재하지 않습니다.")
  public SuccessResponse delete(@Parameter(description = "삭제할 리뷰들의 ID 목록", required = true, example = "[1, 2, 3]") @RequestParam Set<Long> reviewIds) {
    reviewCommandService.delete(reviewIds);
    return new SuccessResponse();
  }
}
