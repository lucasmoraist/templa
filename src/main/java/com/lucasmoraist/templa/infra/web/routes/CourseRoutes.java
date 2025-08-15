package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.course.CreateCourseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

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
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", description = "Bearer token for authentication", required = true),
            @Parameter(name = "teacherId", description = "ID of the teacher creating the course", required = true)
    })
    @PostMapping("create/{teacherId}")
    ResponseEntity<?> createCourse(
            @RequestHeader("Authorization") String authorization,
            @PathVariable UUID teacherId,
            @Valid @RequestBody CreateCourseRequest request);

    @Operation(
            summary = "Get course by ID",
            description = "Retrieves a course by its ID.",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Course not found")
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", description = "Bearer token for authentication", required = true),
            @Parameter(name = "id", description = "ID of the course to retrieve", required = true)
    })
    @GetMapping("/{id}")
    ResponseEntity<?> getCourseById(@RequestHeader("Authorization") String authorization, @PathVariable UUID id);

}
