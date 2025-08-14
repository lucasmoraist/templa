package com.lucasmoraist.templa.domain.enums;

import lombok.Getter;

@Getter
public enum Modality {
    PRESENTIAL("Presential"),
    ONLINE("Online"),
    HYBRID("Hybrid");

    private final String description;

    Modality(String description) {
        this.description = description;
    }

}
