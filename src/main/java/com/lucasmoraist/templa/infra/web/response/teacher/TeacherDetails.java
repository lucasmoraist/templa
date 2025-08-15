package com.lucasmoraist.templa.infra.web.response.teacher;

import com.lucasmoraist.templa.infra.web.response.course.CourseTeacherDetails;

import java.util.List;

public record TeacherDetails(
        String id,
        String name,
        String email,
        String role,
        List<CourseTeacherDetails> courses
) {

}
