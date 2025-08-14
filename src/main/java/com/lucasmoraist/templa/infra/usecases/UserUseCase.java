package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.application.gateway.UserGateway;
import com.lucasmoraist.templa.application.usecases.user.UserLoginCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCase {

    @Bean
    public UserLoginCase userLoginCase(UserGateway userGateway, TokenGateway tokenGateway) {
        return new UserLoginCase(userGateway, tokenGateway);
    }

}
