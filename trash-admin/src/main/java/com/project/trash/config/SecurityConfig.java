package com.project.trash.config;

import com.project.trash.admin.service.AdminQueryService;
import com.project.trash.auth.filter.JwtAuthenticationFilter;
import com.project.trash.auth.service.JwtService;
import com.project.trash.common.exception.handler.CustomAccessDeniedHandler;
import com.project.trash.common.exception.handler.CustomAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Value("${security.cors.origins}")
  private List<String> origins;

  private final JwtService jwtService;
  private final AdminQueryService adminQueryService;

  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        // CSRF 보안 비활성화
        .csrf(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        // 세션을 생성하지 않게 설정
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            (authorize) -> authorize.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/doc", "/health").permitAll()
                                    .anyRequest().authenticated())
        .addFilterBefore(new JwtAuthenticationFilter(jwtService, adminQueryService, customAuthenticationEntryPoint),
            UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(it -> {
          it.authenticationEntryPoint(customAuthenticationEntryPoint);
          it.accessDeniedHandler(customAccessDeniedHandler);
        })
        .securityContext((securityContext) -> securityContext.requireExplicitSave(false))
        .cors(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource(SecurityProperties securityProperties) {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(origins);
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(true);
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }
}
