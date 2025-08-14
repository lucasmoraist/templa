package com.lucasmoraist.templa.application.usecases.user;

import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.application.gateway.UserGateway;
import com.lucasmoraist.templa.domain.model.Token;
import com.lucasmoraist.templa.domain.model.User;

public class UserLoginCase {

    private final UserGateway userGateway;
    private final TokenGateway tokenGateway;

    public UserLoginCase(UserGateway userGateway, TokenGateway tokenGateway) {
        this.userGateway = userGateway;
        this.tokenGateway = tokenGateway;
    }

    public Token execute(String email, String password) {
        User user = this.userGateway.verifyCredentials(email, password);
        return this.tokenGateway.generateToken(user);
    }

}
