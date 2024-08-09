package com.project.trash.auth.controller;

import com.project.trash.auth.controller.validation.AuthValidator;
import com.project.trash.auth.request.LoginRequest;
import com.project.trash.auth.service.AuthService;
import com.project.trash.common.response.DataResponse;
import com.project.trash.member.domain.enums.SocialType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * 인증 API
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  /**
   * 소셜 타입에 따른 AuthCode 요청 Url 반환
   */
  @SneakyThrows
  @GetMapping("/login/url")
  public void getLoginUrl(@RequestParam(required = false, value = "socialType") String socialType,
      HttpServletResponse response) {
    response.sendRedirect(authService.getAuthCodeRequestUrl(SocialType.fromCode(socialType)));
  }

  /**
   * 소셜 타입에 따른 AccessToken 반환
   */
  @GetMapping("/token")
  public ResponseEntity<?> getToken(@RequestParam(required = false, value = "socialType") String socialType,
      @RequestParam(required = false, value = "authCode") String authCode) {
    return ResponseEntity.ok(new DataResponse(authService.getAccessToken(SocialType.fromCode(socialType), authCode)));
  }

  /**
   * 로그인
   */
  @PostMapping("/login")
  public ResponseEntity<?> postLogin(@RequestBody LoginRequest param) {
    AuthValidator.validate(param);

    return ResponseEntity.ok(new DataResponse(authService.login(param)));
  }
}