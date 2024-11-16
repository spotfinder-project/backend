package com.project.trash.common.exception.handler;


import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.response.ErrorResponse;
import com.project.trash.common.utils.LogUtils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ErrorResponse handleValidationException(ValidationException e) {
    LogUtils.error("Exception: " + e.getClass().getSimpleName() + "(" + e.getResultCode().getCode() + ")\n" + e.getLocalizedMessage());
    return new ErrorResponse(e.getResultCode());
  }
}
