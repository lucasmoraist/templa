package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.course.CreateCourseRequest;
import com.lucasmoraist.templa.infra.web.response.course.CourseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Course", description = "Course management API")
@RequestMapping("/api/v1/course")
public interface CourseRoutes {

    @Operation(
            summary = "Create a new course",
            description = "Creates a new course with the provided details.",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course created successfully"),
    })
    @PostMapping
    ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CreateCourseRequest request);

}
