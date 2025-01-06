package com.project.trash.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberLoginRequest {

    @Schema(description = "회원 소셜 타입 (K - KAKAO, N - NAVER)", example = "N")
    private String socialType;

    @Schema(description = "인가 코드")
    private String authCode;
}
