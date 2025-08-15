package com.lucasmoraist.templa.domain.model;

import java.util.UUID;

public record Student(
        UUID id,
        String name,
        User user
) {

}
