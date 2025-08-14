package com.lucasmoraist.templa.domain.model;

import com.lucasmoraist.templa.domain.enums.Roles;

import java.util.UUID;

public record User(
        UUID id,
        String email,
        String password,
        Roles role
) {

}
