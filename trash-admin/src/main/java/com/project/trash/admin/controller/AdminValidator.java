package com.project.trash.admin.controller;

import com.project.trash.admin.request.AdminModifyRequest;
import com.project.trash.admin.request.AdminLoginRequest;
import com.project.trash.admin.request.ReissueRequest;
import com.project.trash.common.utils.ValidatorUtils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminValidator {

  public void validate(AdminModifyRequest param) {
    ValidatorUtils.validateEmpty(param.getPassword());
  }

  public void validate(AdminLoginRequest param) {
    ValidatorUtils.validateEmpty(param.getId());
    ValidatorUtils.validateEmpty(param.getPassword());
  }

  public void validate(ReissueRequest param) {
    ValidatorUtils.validateEmpty(param.getId());
  }
}