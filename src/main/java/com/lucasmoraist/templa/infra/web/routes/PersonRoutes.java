package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.infra.web.request.person.CreatePersonRequest;
import com.lucasmoraist.templa.infra.web.response.person.PersonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/person")
@Tag(name = "Person", description = "Person management routes")
public interface PersonRoutes {

    @Operation(summary = "Register a new person", description = "Create a new person in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created successfully"),
    })
    @PostMapping("register")
    ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody CreatePersonRequest request);

}
