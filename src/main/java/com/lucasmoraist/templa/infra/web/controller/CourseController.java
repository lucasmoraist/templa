package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.course.SaveCourseCase;
import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.infra.mapper.CourseMapper;
import com.lucasmoraist.templa.infra.web.request.course.CreateCourseRequest;
import com.lucasmoraist.templa.infra.web.response.course.CourseResponse;
import com.lucasmoraist.templa.infra.web.routes.CourseRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CourseController implements CourseRoutes {

    private final SaveCourseCase saveCourseCase;

    public CourseController(SaveCourseCase saveCourseCase) {
        this.saveCourseCase = saveCourseCase;
    }

    @Override
    public ResponseEntity<CourseResponse> createCourse(CreateCourseRequest request) {
        Course course = CourseMapper.toDomain(request);
        Course savedCourse = saveCourseCase.execute(course);
        CourseResponse response = CourseMapper.toResponse(savedCourse);
        URI location = URI.create("/api/v1/course");
        return ResponseEntity.created(location).body(response);
    }

}
