package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.course.GetCourseByIdCase;
import com.lucasmoraist.templa.application.usecases.course.SaveCourseCase;
import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.infra.mapper.CourseMapper;
import com.lucasmoraist.templa.infra.web.request.course.CreateCourseRequest;
import com.lucasmoraist.templa.infra.web.response.course.CourseResponse;
import com.lucasmoraist.templa.infra.web.routes.CourseRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
public class CourseController implements CourseRoutes {

    private final SaveCourseCase saveCourseCase;
    private final GetCourseByIdCase getCourseByIdCase;

    public CourseController(SaveCourseCase saveCourseCase, GetCourseByIdCase getCourseByIdCase) {
        this.saveCourseCase = saveCourseCase;
        this.getCourseByIdCase = getCourseByIdCase;
    }

    @Override
    public ResponseEntity<CourseResponse> createCourse(CreateCourseRequest request) {
        Course course = CourseMapper.toDomain(request);
        Course savedCourse = saveCourseCase.execute(course);
        CourseResponse response = CourseMapper.toResponse(savedCourse);
        URI location = URI.create("/api/v1/course");
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<CourseResponse> getCourseById(UUID id) {
        Course course = getCourseByIdCase.execute(id);
        CourseResponse response = CourseMapper.toResponse(course);
        return ResponseEntity.ok(response);
    }

}
