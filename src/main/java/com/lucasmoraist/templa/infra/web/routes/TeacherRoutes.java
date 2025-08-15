package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.teacher.CreateTeacherRequest;
import com.lucasmoraist.templa.infra.web.response.teacher.TeacherDetails;
import com.lucasmoraist.templa.infra.web.response.teacher.TeacherResponse;
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

@RequestMapping("/api/v1/teacher")
@Tag(name = "Teacher", description = "Teacher management routes")
public interface TeacherRoutes {

    @Operation(summary = "Register a new teacher", description = "Create a new teacher in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created successfully"),
    })
    @PostMapping("register")
    ResponseEntity<TeacherResponse> create(@Valid @RequestBody CreateTeacherRequest request);

    @Operation(
            summary = "Register a new teacher",
            description = "Create a new teacher in the system",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Teacher created successfully"),
            @ApiResponse(responseCode = "404", description = "Teacher not found"),
    })
    @GetMapping("{id}")
    ResponseEntity<TeacherDetails> getTeacherById(@PathVariable UUID id);

}
