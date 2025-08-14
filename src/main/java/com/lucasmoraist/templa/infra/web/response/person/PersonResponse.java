package com.lucasmoraist.templa.infra.web.response.person;

public record PersonResponse(
        String id,
        String name,
        String email,
        String role
) {

}
