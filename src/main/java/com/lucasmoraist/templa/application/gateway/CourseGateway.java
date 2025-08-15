package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Course;

import java.util.UUID;

public interface CourseGateway {

    Course create(UUID teacherId, Course course);
    Course findById(UUID id);

}
