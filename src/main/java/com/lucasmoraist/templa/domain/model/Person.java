package com.lucasmoraist.templa.domain.model;

import java.util.UUID;

public record Person(
        UUID id,
        String name,
        User user
) {

    public Person withUser(User user) {
        return new Person(this.id, this.name, user);
    }

}
