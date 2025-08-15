package com.lucasmoraist.templa.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record Enrollment(
        UUID id,
        Student student,
        Group group,
        LocalDateTime enrollmentDate
) {

}
