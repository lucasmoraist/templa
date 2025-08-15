package com.lucasmoraist.templa.infra.web.response.student;

public record StudentResponse(
        String id,
        String name,
        String email,
        String role
) {

}
