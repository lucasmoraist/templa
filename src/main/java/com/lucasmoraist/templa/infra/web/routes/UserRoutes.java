package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.domain.model.Token;
import com.lucasmoraist.templa.infra.web.request.user.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/authentication")
@Tag(name = "Authentication", description = "Authentication routes")
public interface UserRoutes {

    @Operation(summary = "Login", description = "Authenticate a user and return a token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid login credentials"),
    })
    @PostMapping
    ResponseEntity<Token> login(@Valid @RequestBody LoginRequest request);

}
