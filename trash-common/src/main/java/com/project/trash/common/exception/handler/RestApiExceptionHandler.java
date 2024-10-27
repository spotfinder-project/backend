package com.project.trash.common.exception.handler;


import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.response.ErrorResponse;
import com.project.trash.common.utils.LogUtils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ErrorResponse handleValidationException(ValidationException e, WebRequest request) {
    LogUtils.error("Exception: " + e.getClass().getSimpleName() + "(" + e.getLocalizedMessage() + ")");
    return new ErrorResponse(e.getResultCode());
  }
}
