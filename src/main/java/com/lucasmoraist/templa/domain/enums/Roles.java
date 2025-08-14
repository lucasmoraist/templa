package com.lucasmoraist.templa.domain.enums;

import lombok.Getter;

@Getter
public enum Roles {
    STUDENT("Student"),
    TEACHER("Teacher");

    private final String role;

    Roles(String role) {
        this.role = role;
    }

}
