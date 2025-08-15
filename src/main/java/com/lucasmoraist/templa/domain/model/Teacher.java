package com.lucasmoraist.templa.domain.model;

import java.util.UUID;

public record Teacher(
        UUID id,
        String name,
        User user
) {

}
