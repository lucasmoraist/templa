package com.lucasmoraist.templa.application.usecases.course;

import com.lucasmoraist.templa.application.gateway.CourseGateway;
import com.lucasmoraist.templa.domain.model.Course;

import java.util.UUID;

public class GetCourseByIdCase {

    private final CourseGateway courseGateway;

    public GetCourseByIdCase(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    public Course execute(UUID id) {
        return courseGateway.findById(id);
    }

}
