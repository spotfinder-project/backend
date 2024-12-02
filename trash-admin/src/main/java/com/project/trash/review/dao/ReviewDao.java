package com.project.trash.review.dao;

import com.project.trash.common.utils.DateTimeUtils;
import com.project.trash.facility.request.FacilityReviewListRequest;
import com.project.trash.facility.response.FacilityReviewListResponse;
import com.project.trash.member.request.MemberReviewListRequest;
import com.project.trash.member.response.MemberReviewListResponse;
import com.project.trash.review.request.ReviewListRequest;
import com.project.trash.review.response.ReviewListResponse;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
   * 리뷰 목록 총개수
   */
  public Long count(ReviewListRequest param) {
    return dsl.selectCount()
      .from(REVIEW)
      .leftJoin(FACILITY)
      .on(FACILITY.FCLTY_ID.eq(REVIEW.FCLTY_ID))
      .leftJoin(MEMBER)
      .on(MEMBER.MBR_ID.eq(REVIEW.MBR_ID))
      .where(getConditions(param))
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

  /**
   * 리뷰 목록 조회
   */
  public List<ReviewListResponse> select(ReviewListRequest param) {
    return dsl.select(REVIEW, FACILITY, MEMBER.MBR_NCK_NM)
      .from(REVIEW)
      .leftJoin(FACILITY)
      .on(FACILITY.FCLTY_ID.eq(REVIEW.FCLTY_ID))
      .leftJoin(MEMBER)
      .on(MEMBER.MBR_ID.eq(REVIEW.MBR_ID))
      .where(getConditions(param))
      .orderBy(REVIEW.CRE_DTM.desc())
      .limit(param.getSize())
      .offset(param.getOffset())
      .fetchInto(ReviewListResponse.class);
  }

  /**
   * 리뷰 목록 조회 조건 목록
   */
  public List<Condition> getConditions(ReviewListRequest param) {
    List<Condition> conditions = new ArrayList<>();

    // 닉네임
    if (StringUtils.isNotBlank(param.getNickname())) {
      conditions.add(DSL.condition(MEMBER.MBR_NCK_NM.like("%" + param.getNickname() + "%")));
    }
    // 시설물명
    if (StringUtils.isNotBlank(param.getFacilityName())) {
      conditions.add(DSL.condition(FACILITY.FCLTY_NM.like("%" + param.getFacilityName() + "%")));
    }
    // 리뷰 내용
    if (StringUtils.isNotBlank(param.getContent())) {
      conditions.add(DSL.condition(REVIEW.RVW_CTT.like("%" + param.getContent() + "%")));
    }
    // 생성일 검색 시작일
    if (StringUtils.isNotBlank(param.getStartDate())) {
      LocalDateTime startDate = DateTimeUtils.convertDateStringToDateTime(param.getStartDate());
      conditions.add(DSL.condition(REVIEW.CRE_DTM.ge(startDate)));
    }
    // 생성일 검색 종료일
    if (StringUtils.isNotBlank(param.getEndDate())) {
      LocalDateTime endDate = DateTimeUtils.convertToDate(param.getEndDate()).atTime(LocalTime.MAX);
      conditions.add(DSL.condition(REVIEW.CRE_DTM.le(endDate)));
    }

    return conditions;
  }
}
