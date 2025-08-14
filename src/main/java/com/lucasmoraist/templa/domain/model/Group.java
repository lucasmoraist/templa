package com.lucasmoraist.templa.domain.model;

import com.lucasmoraist.templa.domain.enums.DayOfWeek;

import java.util.UUID;

public record Group(
        UUID id,
        DayOfWeek dayOfWeek,
        String time,
        String duration,
        int maxStudents,
        Course course
) {

}
