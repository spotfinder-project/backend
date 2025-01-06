package com.project.trash.member.domain.enums;

import com.project.trash.common.domain.enums.Codable;
import lombok.Getter;

@Getter
public enum Role implements Codable {
    MEMBER("MEMBER"),
    ADMIN("ADMIN");

    private final String code;

    Role(String code) {
        this.code = code;
    }

    public static Role fromCode(String code) {
        return Codable.fromCode(Role.class, code);
    }
}
