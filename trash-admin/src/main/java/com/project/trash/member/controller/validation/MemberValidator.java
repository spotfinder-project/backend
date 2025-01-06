package com.project.trash.member.controller.validation;

import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.utils.DateTimeUtils;
import com.project.trash.common.utils.ValidatorUtils;
import com.project.trash.member.request.*;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

import static com.project.trash.common.domain.resultcode.RequestResultCode.PARAM_INVALID;

@UtilityClass
public class MemberValidator {

  public void validate(MemberReviewModifyRequest param) {
    ValidatorUtils.validateNull(param.getReviewId());
    ValidatorUtils.validateEmpty(param.getContent());
  }

  public void validate(MemberListRequest param) {
    String startDate = param.getStartDate();
    if (StringUtils.isNotBlank(startDate) && !DateTimeUtils.validFormat(startDate)) {
      throw new ValidationException(PARAM_INVALID);
    }
    String endDate = param.getEndDate();
    if (StringUtils.isNotBlank(endDate) && !DateTimeUtils.validFormat(endDate)) {
      throw new ValidationException(PARAM_INVALID);
    }
    if (!DateTimeUtils.isBeforeDate(startDate, endDate)) {
      throw new ValidationException(PARAM_INVALID);
    }
  }

  public void validate(MemberFacilityListRequest param) {
    ValidatorUtils.validateEmpty(param.getMemberId());
  }

  public void validate(MemberReviewListRequest param) {
    ValidatorUtils.validateNull(param.getMemberId());
  }

  public void validate(MemberSignupHistoryRequest param) {
    String startDate = param.getStartDate();
    if (StringUtils.isBlank(startDate) || !DateTimeUtils.validFormat(startDate)) {
      throw new ValidationException(PARAM_INVALID);
    }
    String endDate = param.getEndDate();
    if (StringUtils.isBlank(endDate) || !DateTimeUtils.validFormat(endDate)) {
      throw new ValidationException(PARAM_INVALID);
    }
    if (!DateTimeUtils.isBeforeDate(startDate, endDate)) {
      throw new ValidationException(PARAM_INVALID);
    }
  }

  public void validate(MemberLoginRequest param) {
    if (StringUtils.isAnyBlank(param.getSocialType(), param.getAuthCode())) {
      throw new ValidationException(PARAM_INVALID);
    }
  }
}
