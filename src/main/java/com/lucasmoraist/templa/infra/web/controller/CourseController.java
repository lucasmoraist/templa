package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.course.GetCourseByIdCase;
import com.lucasmoraist.templa.application.usecases.course.SaveCourseCase;
import com.lucasmoraist.templa.application.usecases.token.GetRoleByTokenCase;
import com.lucasmoraist.templa.domain.enums.Roles;
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
    private final GetRoleByTokenCase getRoleByTokenCase;

    public CourseController(SaveCourseCase saveCourseCase, GetCourseByIdCase getCourseByIdCase, GetRoleByTokenCase getRoleByTokenCase) {
        this.saveCourseCase = saveCourseCase;
        this.getCourseByIdCase = getCourseByIdCase;
        this.getRoleByTokenCase = getRoleByTokenCase;
    }

    @Override
    public ResponseEntity<CourseResponse> createCourse(String authorization, CreateCourseRequest request) {
        Course course = CourseMapper.toDomain(request);
        Course savedCourse = saveCourseCase.execute(course);
        Roles role = getRoleByTokenCase.execute(authorization);
        CourseResponse response = CourseMapper.toResponse(savedCourse, role);
        URI location = URI.create("/api/v1/course");
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<CourseResponse> getCourseById(String authorization, UUID id) {
        Course course = getCourseByIdCase.execute(id);
        Roles role = getRoleByTokenCase.execute(authorization);
        CourseResponse response = CourseMapper.toResponse(course, role);
        return ResponseEntity.ok(response);
    }

}
