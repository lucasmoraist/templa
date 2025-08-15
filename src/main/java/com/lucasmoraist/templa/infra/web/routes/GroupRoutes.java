package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.group.GroupRequest;
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

@RequestMapping("/api/v1/group")
@Tag(name = "Group", description = "Group management API")
public interface GroupRoutes {

    @Operation(
            summary = "Create a new group",
            description = "Creates a new group for the specified course. The group will be associated with the course ID " +
                          "provided.",
            security = @SecurityRequirement(name = "bearer")

    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Group created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Course not found"),
    })
    @Parameter(name = "courseId", description = "ID of the course to create the group for", required = true)
    @PostMapping("/{courseId}")
    ResponseEntity<?> createGroup(@PathVariable UUID courseId, @Valid @RequestBody GroupRequest request);

    @Operation(
            summary = "Get group by ID",
            description = "Retrieves a group by its unique identifier. The group must exist in the system.",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Group not found")
    })
    @Parameters(value = {
            @Parameter(name = "Authorization", description = "Bearer token for authentication", required = true),
            @Parameter(name = "id", description = "ID of the group to retrieve", required = true)
    })
    @GetMapping("/{id}")
    ResponseEntity<?> getGroupById(@RequestHeader("Authorization") String authorization, @PathVariable UUID id);

}
