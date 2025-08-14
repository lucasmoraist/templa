package com.lucasmoraist.templa.infra.web.response.group;

import com.lucasmoraist.templa.infra.web.response.course.CourseResponse;

public record GroupResponse(
        String id,
        String dayOfWeek,
        String time,
        String duration,
        int maxStudents,
        CourseResponse course
) {

}
