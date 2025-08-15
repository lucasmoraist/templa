package com.lucasmoraist.templa.application.usecases.person;

import com.lucasmoraist.templa.application.gateway.PersonGateway;
import com.lucasmoraist.templa.domain.model.Person;

import java.util.UUID;

public class GetPersonByIdCase {

    private final PersonGateway personGateway;

    public GetPersonByIdCase(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    public Person findById(UUID id) {
        return this.personGateway.findById(id);
    }

}
