package com.lucasmoraist.templa.application.usecases.course;

import com.lucasmoraist.templa.application.gateway.CourseGateway;
import com.lucasmoraist.templa.domain.model.Course;

public class SaveCourseCase {

    private final CourseGateway courseGateway;

    public SaveCourseCase(CourseGateway courseGateway) {
        this.courseGateway = courseGateway;
    }

    public Course execute(Course course) {
        return courseGateway.create(course);
    }

}
