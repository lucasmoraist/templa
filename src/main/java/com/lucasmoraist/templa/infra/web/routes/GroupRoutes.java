package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.group.GroupRequest;
import com.lucasmoraist.templa.infra.web.response.group.GroupResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/v1/group")
public interface GroupRoutes {

    @Operation(
            summary = "Create a new group",
            description = "Creates a new group for the specified course. The group will be associated with the course ID " +
                          "provided.",
            security = @SecurityRequirement(name = "bearer")

    )
    @PostMapping("/{courseId}")
    ResponseEntity<GroupResponse> createGroup(@PathVariable UUID courseId, @Valid @RequestBody GroupRequest request);

}
