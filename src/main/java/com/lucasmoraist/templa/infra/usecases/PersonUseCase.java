package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.PersonGateway;
import com.lucasmoraist.templa.application.usecases.person.SavePersonCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonUseCase {

    @Bean
    public SavePersonCase savePersonCase(PersonGateway personGateway) {
        return new SavePersonCase(personGateway);
    }

}
