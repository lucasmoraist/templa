package com.lucasmoraist.templa.infra.web.response;

public record PersonResponse(
        String id,
        String name,
        String email,
        String role
) {

}
