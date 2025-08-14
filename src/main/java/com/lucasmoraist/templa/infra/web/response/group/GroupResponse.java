package com.lucasmoraist.templa.infra.web.response.group;

public record GroupResponse(
        String id,
        String dayOfWeek,
        String time,
        String duration,
        int maxStudents
) {

}
