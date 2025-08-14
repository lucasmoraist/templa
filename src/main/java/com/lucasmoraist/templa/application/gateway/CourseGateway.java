package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Course;

import java.util.UUID;

public interface CourseGateway {

    Course create(Course course);
    Course findById(UUID id);

}
