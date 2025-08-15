package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.person.CreatePersonRequest;
import com.lucasmoraist.templa.infra.web.response.person.PersonResponse;
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

@RequestMapping("/api/v1/person")
@Tag(name = "Person", description = "Person management routes")
public interface PersonRoutes {

    @Operation(summary = "Register a new person", description = "Create a new person in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully"),
    })
    @PostMapping("register")
    ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request);

    @Operation(
            summary = "Register a new person",
            description = "Create a new person in the system",
            security = @SecurityRequirement(name = "bearer")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
    })
    @GetMapping("{id}")
    ResponseEntity<PersonResponse> getPersonById(@PathVariable UUID id);

}
