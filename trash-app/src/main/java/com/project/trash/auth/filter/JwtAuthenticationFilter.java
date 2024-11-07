package com.project.trash.auth.filter;

import com.project.trash.auth.service.JwtService;
import com.project.trash.common.constant.PathConstant;
import com.project.trash.common.exception.ValidationException;
import com.project.trash.common.exception.handler.CustomAuthenticationEntryPoint;
import com.project.trash.member.domain.MemberDetail;
import com.project.trash.member.service.MemberQueryService;
import com.project.trash.token.domain.Token;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * JwtAuthenticationFilter
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final MemberQueryService memberQueryService;
  private final CustomAuthenticationEntryPoint authenticationEntryPoint;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String path = request.getServletPath();

    if (Pattern.matches(PathConstant.SWAGGER_PATHS, path)) {
      filterChain.doFilter(request, response);
      return;
    }

    try {
      String accessToken = jwtService.extractToken(request);
      if (StringUtils.isBlank(accessToken)) {
        filterChain.doFilter(request, response);
        return;
      }

      String socialId = jwtService.extractSocialId(accessToken);
      if (StringUtils.isNotBlank(socialId)) {
        MemberDetail memberDetail = new MemberDetail(memberQueryService.getOne(socialId));

        Optional<Token> token = memberQueryService.getToken(socialId);
        if (token.isEmpty() || !token.get().getAccessToken().equals(accessToken)) {
          filterChain.doFilter(request, response);
          return;
        }

        // 유효성 체크
        if (jwtService.isTokenValid(accessToken, memberDetail)) {
          Authentication authentication =
              new UsernamePasswordAuthenticationToken(memberDetail, accessToken, memberDetail.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }

      filterChain.doFilter(request, response);
    } catch (Exception e) {
      String message = e instanceof ValidationException ? ((ValidationException) e).getResultCode().getMessage() : e.getMessage();
      authenticationEntryPoint.commence(request, response, new AuthenticationException(message, e) {});
    }
  }
}
