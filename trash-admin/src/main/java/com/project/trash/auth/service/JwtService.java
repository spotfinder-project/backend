package com.project.trash.auth.service;

import com.project.trash.admin.domain.Admin;
import com.project.trash.auth.config.JwtConfig;

import com.project.trash.member.domain.enums.Role;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

  private final JwtConfig jwtConfig;

  public Pair<String, Long> createAccessToken(String socialId, Role role) {
    String token = Jwts.builder()
                       .setSubject(socialId)
                       .setIssuedAt(new Date(System.currentTimeMillis()))
                       .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.accessExpiration()))
                       .claim("role", role.getCode())
                       .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                       .compact();

    return Pair.of(token, jwtConfig.accessExpiration());
  }

  public Pair<String, Long> createRefreshToken(String socialId) {
    String token = Jwts.builder()
                       .setSubject(socialId)
                       .setIssuedAt(new Date(System.currentTimeMillis()))
                       .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.refreshExpiration()))
                       .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                       .compact();

    return Pair.of(token, jwtConfig.refreshExpiration());
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Role extractRole(String token) {
    return Role.fromCode(extractClaim(token, claims -> claims.get("role", String.class)));
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  public boolean isTokenValid(String token, Admin admin) {
    final String username = extractUsername(token);
    return (username.equals(admin.getId())) && !isTokenExpired(token);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(jwtConfig.secretKey());
    return Keys.hmacShaKeyFor(keyBytes);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
}
