package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.student.CreateStudentRequest;
import com.lucasmoraist.templa.infra.web.response.student.StudentResponse;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/v1/student")
@Tag(name = "Student", description = "Student management routes")
public interface StudentRoutes {

    @Operation(summary = "Register a new student", description = "Create a new student in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
    })
    @PostMapping("register")
    ResponseEntity<StudentResponse> create(@Valid @RequestBody CreateStudentRequest request);

    @Operation(
            summary = "Register a new student",
            description = "Create a new student in the system",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully"),
            @ApiResponse(responseCode = "404", description = "Student not found"),
    })
    @GetMapping("{id}")
    ResponseEntity<StudentDetails> getStudentById(@PathVariable UUID id);

}
