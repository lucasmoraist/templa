package com.lucasmoraist.templa.domain.model;

public record Token(
        String accessToken,
        int expiresIn
) {

}
