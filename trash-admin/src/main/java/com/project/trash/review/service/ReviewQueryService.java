package com.project.trash.review.service;

import com.project.trash.common.exception.ValidationException;
import com.project.trash.facility.domain.Review;
import com.project.trash.facility.repository.ReviewRepository;
import com.project.trash.facility.request.FacilityReviewListRequest;
import com.project.trash.facility.response.FacilityReviewListResponse;
import com.project.trash.member.request.MemberReviewListRequest;
import com.project.trash.member.response.MemberReviewListResponse;
import com.project.trash.review.dao.ReviewDao;
import com.project.trash.review.request.ReviewListRequest;
import com.project.trash.review.response.ReviewListResponse;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static com.project.trash.common.domain.resultcode.ReviewResultCode.REVIEW_NOT_FOUND;

/**
 * 리뷰 조회 서비스
 */
@Service
@RequiredArgsConstructor
public class ReviewQueryService {

  private final ReviewRepository reviewRepository;
  private final ReviewDao reviewDao;

  /**
   * 회원이 등록한 리뷰 목록 조회
   */
  @Transactional(readOnly = true)
  public Pair<List<MemberReviewListResponse>, Long> getList(MemberReviewListRequest param) {
    return Pair.of(reviewDao.select(param), reviewDao.count(param.getMemberId()));
  }

  /**
   * 시설물 리뷰 목록 조회
   */
  @Transactional(readOnly = true)
  public Pair<List<FacilityReviewListResponse>, Long> getList(FacilityReviewListRequest param) {
    return Pair.of(reviewDao.select(param), reviewDao.count(param));
  }

  /**
   * 리뷰 목록 조회
   */
  @Transactional(readOnly = true)
  public Pair<List<ReviewListResponse>, Long> getList(ReviewListRequest param) {
    return Pair.of(reviewDao.select(param), reviewDao.count(param));
  }

  /**
   * 리뷰 조회
   */
  @Transactional(readOnly = true)
  public Review getOne(Long reviewId) {
    return reviewRepository.findById(reviewId).orElseThrow(() -> new ValidationException(REVIEW_NOT_FOUND));
  }
}
