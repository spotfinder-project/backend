package com.project.trash.common.exception;

import com.project.trash.common.domain.ResultCode;

import lombok.Getter;

/**
 * 유효성 검사 에러
 */
@Getter
public class ValidationException extends RuntimeException {

  private final ResultCode resultCode;

  public ValidationException(ResultCode resultCode) {
    super(resultCode.getMessage());
    this.resultCode = resultCode;
  }

  public ValidationException(ResultCode resultCode, Exception e) {
    super(e.getMessage());
    this.resultCode = resultCode;
  }
}
