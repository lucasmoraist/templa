package com.lucasmoraist.templa.infra.web.request.user;

public record LoginRequest(
        String email,
        String password
) {

}
