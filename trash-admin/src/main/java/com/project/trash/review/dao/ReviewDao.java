package com.project.trash.review.dao;

import com.project.trash.facility.request.FacilityReviewListRequest;
import com.project.trash.facility.response.FacilityReviewListResponse;
import com.project.trash.member.request.MemberReviewListRequest;
import com.project.trash.member.response.MemberReviewListResponse;

import org.jooq.DSLContext;
import org.jooq.types.ULong;
import org.springframework.stereotype.Repository;

import java.util.List;

import lombok.RequiredArgsConstructor;

import static trash.Tables.FACILITY;
import static trash.Tables.MEMBER;
import static trash.Tables.REVIEW;

/**
 * 리뷰 조회 DAO
 */
@Repository
@RequiredArgsConstructor
public class ReviewDao {

  private final DSLContext dsl;

  /**
   * 회원이 등록한 리뷰 목록 총개수
   */
  public Long count(Long memberId) {
    return dsl.selectCount()
              .from(REVIEW)
              .where(REVIEW.MBR_ID.eq(ULong.valueOf(memberId)))
              .fetchOneInto(Long.class);
  }

  /**
   * 시설물 리뷰 목록 총개수
   */
  public Long count(FacilityReviewListRequest param) {
    return dsl.selectCount()
              .from(REVIEW)
              .where(REVIEW.FCLTY_ID.eq(ULong.valueOf(param.getFacilityId())))
              .fetchOneInto(Long.class);
  }

  /**
   * 회원이 등록한 리뷰 목록 조회
   */
  public List<MemberReviewListResponse> select(MemberReviewListRequest param) {
    return dsl.select(REVIEW.RVW_ID, REVIEW.RVW_CTT, REVIEW.CRE_DTM, FACILITY)
        .from(REVIEW)
        .leftJoin(FACILITY)
        .on(FACILITY.FCLTY_ID.eq(REVIEW.FCLTY_ID))
        .where(REVIEW.MBR_ID.eq(ULong.valueOf(param.getMemberId())))
        .orderBy(REVIEW.CRE_DTM.desc())
        .limit(param.getSize())
        .offset(param.getOffset())
        .fetchInto(MemberReviewListResponse.class);
  }

  /**
   * 시설물 리뷰 목록 조회
   */
  public List<FacilityReviewListResponse> select(FacilityReviewListRequest param) {
    return dsl.select(REVIEW.RVW_ID, REVIEW.RVW_CTT, REVIEW.CRE_DTM, REVIEW.MBR_ID, MEMBER.MBR_NCK_NM)
        .from(REVIEW)
        .leftJoin(MEMBER)
        .on(MEMBER.MBR_ID.eq(REVIEW.MBR_ID))
        .where(REVIEW.FCLTY_ID.eq(ULong.valueOf(param.getFacilityId())))
        .orderBy(REVIEW.CRE_DTM.desc())
        .limit(param.getSize())
        .offset(param.getOffset())
        .fetchInto(FacilityReviewListResponse.class);
  }
}
