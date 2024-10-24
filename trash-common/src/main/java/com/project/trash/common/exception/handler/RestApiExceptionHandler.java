package com.project.trash.common.exception.handler;


import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.response.ErrorResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ValidationException.class)
  public ErrorResponse handleValidationException(ValidationException e, WebRequest request) {
    log.info("Exception: " + e.getClass().getSimpleName() + "(" + e.getLocalizedMessage() + ")");
    final Locale locale = request.getLocale();

    return new ErrorResponse(e.getResultCode());
  }
}
