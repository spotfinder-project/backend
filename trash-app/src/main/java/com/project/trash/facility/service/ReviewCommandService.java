package com.project.trash.facility.service;

import com.project.trash.common.exception.ValidationException;
import com.project.trash.facility.domain.Review;
import com.project.trash.facility.repository.FacilityRepository;
import com.project.trash.facility.repository.ReviewRepository;
import com.project.trash.facility.request.ReviewEntryRequest;
import com.project.trash.facility.request.ReviewModifyRequest;
import com.project.trash.utils.MemberUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.FacilityResultCode.FACILITY_NOT_FOUND;

/**
 * 리뷰 등록/수정/삭제 서비스
 */
@Service
@RequiredArgsConstructor
public class ReviewCommandService {

  private final ReviewRepository reviewRepository;
  private final ReviewQueryService reviewQueryService;

  private final FacilityRepository facilityRepository;

  /**
   * 리뷰 삭제
   */
  @Transactional
  public void delete(Long reviewId) {
    Review review = reviewQueryService.getOne(reviewId, MemberUtils.getMemberId());
    // 시설물 존재여부 검증
    verifyFacilityExist(review.getFacilityId());

    reviewRepository.delete(review);
  }

  /**
   * 리뷰 등록
   */
  @Transactional
  public void entry(ReviewEntryRequest param) {
    // 시설물 존재여부 검증
    verifyFacilityExist(param.getFacilityId());

    reviewRepository.save(new Review(param.getContent(), param.getFacilityId()));
  }

  /**
   * 리뷰 수정
   */
  @Transactional
  public void modify(ReviewModifyRequest param) {
    Review review = reviewQueryService.getOne(param.getReviewId(), MemberUtils.getMemberId());
    // 시설물 존재여부 검증
    verifyFacilityExist(review.getFacilityId());

    review.update(param.getContent());
  }

  /**
   * 시설물 존재여부 검증
   */
  private void verifyFacilityExist(Long facilityId) {
    if (!facilityRepository.existsById(facilityId)) {
      throw new ValidationException(FACILITY_NOT_FOUND);
    }
  }
}
