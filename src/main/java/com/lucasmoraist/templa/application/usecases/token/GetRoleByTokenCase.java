package com.lucasmoraist.templa.application.usecases.token;

import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.domain.enums.Roles;

public class GetRoleByTokenCase {

    private final TokenGateway tokenGateway;

    public GetRoleByTokenCase(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public Roles execute(String token) {
        return tokenGateway.getRoleFromToken(token);
    }

}
