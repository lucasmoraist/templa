package com.lucasmoraist.templa.domain.model;

import java.util.UUID;

public record Person(
        UUID id,
        String name,
        User user
) {

}
