package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Token;
import com.lucasmoraist.templa.domain.model.User;

public interface TokenGateway {

    Token generateToken(User user);
    String validateAndGetSubject(String token);

}
