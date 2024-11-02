package com.project.trash.member.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberNicknameModifyRequest {

  @Schema(description = "닉네임", example = "SBS")
  private String nickname;
}
