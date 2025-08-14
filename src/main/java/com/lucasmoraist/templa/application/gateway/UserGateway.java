package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.User;

public interface UserGateway {

    User verifyCredentials(String email, String password);

}
