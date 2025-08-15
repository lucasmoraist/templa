package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.application.usecases.token.GetRoleByTokenCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenUseCase {

    @Bean
    public GetRoleByTokenCase getRoleByTokenCase(TokenGateway tokenGateway) {
        return new GetRoleByTokenCase(tokenGateway);
    }

}
