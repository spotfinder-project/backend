package com.project.trash.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberUnlinkRequest {

    @Schema(description = "인가 코드")
    private String authCode;
}
