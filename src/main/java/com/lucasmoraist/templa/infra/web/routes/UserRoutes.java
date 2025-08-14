package com.lucasmoraist.templa.infra.web.routes;

import com.lucasmoraist.templa.domain.model.Token;
import com.lucasmoraist.templa.infra.web.request.user.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/authentication")
public interface UserRoutes {

    @PostMapping
    ResponseEntity<Token> login(@Valid @RequestBody LoginRequest request);

}
