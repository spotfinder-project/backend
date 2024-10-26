package com.project.trash.member.dao;

import com.project.trash.common.utils.DateTimeUtils;
import com.project.trash.member.request.MemberListRequest;
import com.project.trash.member.request.MemberSignupHistoryRequest;
import com.project.trash.member.response.MemberListResponse;
import com.project.trash.member.response.MemberSignupHistoryResponse;

import org.apache.commons.lang3.StringUtils;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Row1;
import org.jooq.Table;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static org.jooq.impl.DSL.row;
import static trash.Tables.MEMBER;

/**
 * 회원 조회 DAO
 */
@Repository
@RequiredArgsConstructor
public class MemberDao {

  private final DSLContext dsl;

  /**
   * 회원 목록 조회 총개수
   */
  public Long count(MemberListRequest param) {
    return dsl.selectCount()
              .from(MEMBER)
              .where(getConditions(param))
              .fetchOneInto(Long.class);
  }

  /**
   * 회원 목록 조회
   */
  public List<MemberListResponse> select(MemberListRequest param) {
    return dsl.selectFrom(MEMBER)
              .where(getConditions(param))
              .orderBy(MEMBER.CRE_DTM.desc())
              .limit(param.getSize())
              .offset(param.getOffset())
              .fetch()
              .map(MemberListResponse::new);
  }

  /**
   * 회원 목록 조회 조건 목록
   */
  public List<Condition> getConditions(MemberListRequest param) {
    List<Condition> conditions = new ArrayList<>();

    // 회원 ID
    if (param.getMemberId() != null) {
      conditions.add(DSL.condition(MEMBER.MBR_ID.eq(ULong.valueOf(param.getMemberId()))));
    }
    // 닉네임
    if (StringUtils.isNotBlank(param.getNickname())) {
      conditions.add(DSL.condition(MEMBER.MBR_NCK_NM.like("%" + param.getNickname() + "%")));
    }
    // 성별
    if (StringUtils.isNotBlank(param.getGender())) {
      conditions.add(DSL.condition(MEMBER.MBR_GNDR.eq(param.getGender())));
    }
    // 소셜 타입
    if (StringUtils.isNotBlank(param.getSocialType())) {
      conditions.add(DSL.condition(MEMBER.MBR_SCL_TYP.eq(param.getSocialType())));
    }
    // 생성일 검색 시작일
    if (StringUtils.isNotBlank(param.getStartDate())) {
      LocalDateTime startDate = DateTimeUtils.convertDateStringToDateTime(param.getStartDate());
      conditions.add(DSL.condition(MEMBER.CRE_DTM.ge(startDate)));
    }
    // 생성일 검색 종료일
    if (StringUtils.isNotBlank(param.getEndDate())) {
      LocalDateTime endDate = DateTimeUtils.convertToDate(param.getEndDate()).atTime(LocalTime.MAX);
      conditions.add(DSL.condition(MEMBER.CRE_DTM.le(endDate)));
    }
    return conditions;
  }

  /**
   * 회원가입 이력 조회
   */
  public List<MemberSignupHistoryResponse> select(MemberSignupHistoryRequest param) {
    LocalDate startDate = DateTimeUtils.convertToDate(param.getStartDate());
    LocalDate endDate = DateTimeUtils.convertToDate(param.getEndDate());

    // 시작일과 종료일 사이의 날짜 리스트 생성
    List<LocalDate> dateList = startDate.datesUntil(endDate.plusDays(1))
                                        .toList();

    Table<?> dateSeries = DSL.values(dateList.stream()
                                             .map(date -> row(date))
                                             .toArray(Row1[]::new)
    ).as("date_series", "date");

    Field<LocalDate> createdDate = MEMBER.CRE_DTM.cast(LocalDate.class);

    return dsl.select(dateSeries.field("date"), DSL.count(MEMBER.MBR_ID))
              .from(dateSeries)
              .leftJoin(MEMBER)
              .on(dateSeries.field("date").cast(LocalDate.class).eq(createdDate))
              .groupBy(dateSeries.field("date"))
              .orderBy(dateSeries.field("date").asc())
              .fetchInto(MemberSignupHistoryResponse.class);
  }
}
