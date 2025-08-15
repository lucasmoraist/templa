package com.lucasmoraist.templa.infra.web.routes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/v1/enrollment")
@Tag(name = "Enrollment", description = "Enrollment management API")
public interface EnrollmentRoutes {

    @Operation(
            summary = "Enroll in a group",
            description = "Enrolls the authenticated student in the specified group.",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully enrolled in the group"),
            @ApiResponse(responseCode = "404", description = "Group or Student not found"),
            @ApiResponse(responseCode = "403", description = "Group is full or token is invalid")
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", description = "Bearer token for authentication", required = true),
            @Parameter(name = "groupId", description = "ID of the group to enroll in", required = true)
    })
    @PostMapping("/group/{groupId}")
    ResponseEntity<Void> enroll(@RequestHeader("Authorization") String authorization, @PathVariable UUID groupId);

    @PostMapping("/finalise/{studentId}")
    ResponseEntity<Void> finalise(@PathVariable UUID studentId);

}
