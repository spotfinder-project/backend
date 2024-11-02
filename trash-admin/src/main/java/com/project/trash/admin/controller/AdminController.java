package com.project.trash.admin.controller;

import com.project.trash.admin.request.AdminModifyRequest;
import com.project.trash.admin.request.LoginRequest;
import com.project.trash.admin.request.ReissueRequest;
import com.project.trash.admin.response.ReissueTokenResponse;
import com.project.trash.admin.response.LoginResponse;
import com.project.trash.admin.service.AdminCommandService;
import com.project.trash.common.response.DataResponse;
import com.project.trash.common.response.SuccessResponse;
import com.project.trash.common.utils.CookieUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admins")
@RequiredArgsConstructor
@Tag(name = "관리자")
public class AdminController {

  private final AdminCommandService adminCommandService;

  @PostMapping("/login")
  @Operation(summary = "로그인",
      description = "로그인한다."
          + "\n[에러 코드]"
          + "\n- ADM000 : 관리자 정보가 존재하지 않습니다."
          + "\n- ADM001 : 관리자 정보가 일치하지 않습니다.")
  public DataResponse<LoginResponse> postLogin(@RequestBody LoginRequest param, HttpServletResponse response) {
    AdminValidator.validate(param);

    return new DataResponse<>(adminCommandService.login(param, response));
  }

  @PostMapping("/logout")
  @Operation(summary = "로그아웃",
      description = "로그아웃한다."
          + "\n[에러 코드]"
          + "\n- AUTH000 : 토큰 정보가 존재하지 않습니다.")
  public SuccessResponse postLogout(HttpServletResponse response) {
    adminCommandService.logout(response);
    return new SuccessResponse();
  }

  @PostMapping("/reissue")
  @Operation(summary = "토큰 재발급",
      description = "토큰을 재발급한다."
          + "\n[에러 코드]"
          + "\n- ADM000 : 관리자 정보가 존재하지 않습니다."
          + "\n- AUTH000 : 토큰 정보가 존재하지 않습니다."
          + "\n- AUTH001 : 토큰 정보가 유효하지 않습니다.")
  public DataResponse<ReissueTokenResponse> postReissue(@RequestBody ReissueRequest param, HttpServletRequest request, HttpServletResponse response) {
    AdminValidator.validate(param);

    ReissueTokenResponse res = adminCommandService.reissue(param, request);
    CookieUtils.setCookie("accessToken", res.getAccessToken(), res.getAccessExpiration(), response);
    CookieUtils.setCookie("refreshToken", res.getRefreshToken(), res.getRefreshExpiration(), response);
    return new DataResponse<>(res);
  }

  @PutMapping
  @Operation(summary = "관리자 정보 수정",
      description = "관리자 정보를 수정한다."
          + "\n[에러 코드]"
          + "\n- ADM000 : 관리자 정보가 존재하지 않습니다.")
  public SuccessResponse put(@RequestBody AdminModifyRequest param) {
    AdminValidator.validate(param);

    adminCommandService.modify(param);
    return new SuccessResponse();
  }
}
