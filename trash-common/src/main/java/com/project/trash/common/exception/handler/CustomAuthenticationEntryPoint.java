package com.project.trash.common.exception.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.trash.common.domain.resultcode.RequestResultCode;
import com.project.trash.common.response.ErrorResponse;
import com.project.trash.common.utils.LogUtils;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 인증 예외 핸들러
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) {
    ErrorResponse errorResponse = new ErrorResponse(RequestResultCode.UNAUTHORIZED);
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(HttpStatus.UNAUTHORIZED.value());

    try {
      response.getWriter().print(new ObjectMapper().writeValueAsString(errorResponse));
    } catch (Exception e) {
      LogUtils.error(e.getMessage());
    }
  }
}
