package com.project.trash.member.domain;

import com.project.trash.member.domain.enums.GenderType;
import com.project.trash.member.domain.enums.SocialType;

public record OAuthMember(String socialId, String email, GenderType gender, SocialType socialType) {
}
