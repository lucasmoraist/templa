package com.lucasmoraist.templa.domain.model;

import com.lucasmoraist.templa.domain.enums.Modality;

import java.util.List;
import java.util.UUID;

public record Course(
        UUID id,
        String name,
        String description,
        Modality modality,
        Teacher teacher,
        List<Group> groups
) {

}
