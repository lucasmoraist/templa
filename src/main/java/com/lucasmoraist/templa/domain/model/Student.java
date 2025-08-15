package com.lucasmoraist.templa.domain.model;

import java.util.List;
import java.util.UUID;

public record Student(
        UUID id,
        String name,
        User user,
        List<Enrollment> enrollments
) {

}
