package com.project.trash.facility.dao;

import com.project.trash.facility.domain.enums.FacilityApprovalStatus;
import com.project.trash.facility.request.FacilityListRequest;
import com.project.trash.facility.response.FacilityListResponse;
import com.project.trash.member.response.MyFacilityListResponse;
import com.project.trash.utils.MemberUtils;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import static trash.tables.Facility.FACILITY;

/**
 * 시설물 조회 DAO
 */
@Repository
@RequiredArgsConstructor
public class FacilityDao {

  private final DSLContext dsl;

  public Long count(FacilityListRequest param) {
    return dsl.selectCount()
        .from(FACILITY)
        .where(getCondionList(param))
        .fetchOneInto(Long.class);
  }

  /**
   * 시설물 목록 조회
   */
  public List<FacilityListResponse.FacilityList> select(FacilityListRequest param) {
    return dsl.select(FACILITY.FCLTY_ID, FACILITY.FCLTY_TYP, FACILITY.FCLTY_LTTD, FACILITY.FCLTY_LNGT)
        .from(FACILITY)
        .where(getCondionList(param))
        .orderBy(FACILITY.CRE_DTM.desc())
        .fetchInto(FacilityListResponse.FacilityList.class);
  }

  private List<Condition> getCondionList(FacilityListRequest param) {
    List<Condition> conditions = new ArrayList<>();

    conditions.add(DSL.condition(FACILITY.FCLTY_APRV_STA.eq(FacilityApprovalStatus.APPROVE.getCode())));

    if (param.getType() != null && !param.getType().isEmpty()) {
      conditions.add(DSL.condition(FACILITY.FCLTY_TYP.in(param.getType())));
    }

    return conditions;
  }

  /**
   * 등록한 시설물 목록 조회
   */
  public List<MyFacilityListResponse> select() {
    return dsl.select(FACILITY.FCLTY_ID, FACILITY.FCLTY_TYP, FACILITY.FCLTY_NM,
        FACILITY.FCLTY_DTL_LCTN, FACILITY.FCLTY_INFO)
        .from(FACILITY)
        .where(FACILITY.MBR_ID.eq(String.valueOf(MemberUtils.getMemberId())))
        .and(FACILITY.FCLTY_APRV_STA.eq(FacilityApprovalStatus.APPROVE.getCode()))
        .orderBy(FACILITY.CRE_DTM.desc())
        .fetchInto(MyFacilityListResponse.class);
  }
}
