package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.user.UserLoginCase;
import com.lucasmoraist.templa.domain.model.Token;
import com.lucasmoraist.templa.infra.web.request.user.LoginRequest;
import com.lucasmoraist.templa.infra.web.routes.UserRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserRoutes {

    private final UserLoginCase userLoginCase;

    public UserController(UserLoginCase userLoginCase) {
        this.userLoginCase = userLoginCase;
    }

    @Override
    public ResponseEntity<Token> login(LoginRequest request) {
        Token token = userLoginCase.execute(request.email(), request.password());
        return ResponseEntity.ok().body(token);
    }

}
