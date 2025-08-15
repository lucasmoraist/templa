package com.lucasmoraist.templa.infra.web.response.teacher;

public record TeacherResponse(
        String id,
        String name,
        String email,
        String role
) {

}
