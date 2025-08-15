package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Person;

import java.util.UUID;

public interface PersonGateway {

    Person save(Person person);
    Person findById(UUID id);

}
