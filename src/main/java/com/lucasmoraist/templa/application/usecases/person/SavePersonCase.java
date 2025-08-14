package com.lucasmoraist.templa.application.usecases.person;

import com.lucasmoraist.templa.application.gateway.PersonGateway;
import com.lucasmoraist.templa.domain.model.Person;
import com.lucasmoraist.templa.domain.model.User;

public class SavePersonCase {

    private final PersonGateway personGateway;

    public SavePersonCase(PersonGateway personGateway) {
        this.personGateway = personGateway;
    }

    public Person execute(String name, User user) {
        Person person = new Person(null, name, user);
        return personGateway.save(person);
    }

}
