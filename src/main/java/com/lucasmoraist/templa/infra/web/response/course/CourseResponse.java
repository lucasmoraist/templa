package com.lucasmoraist.templa.infra.web.response.course;

public record CourseResponse(
        String id,
        String name,
        String description,
        String modality
) {

}
