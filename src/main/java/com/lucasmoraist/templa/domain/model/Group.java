package com.lucasmoraist.templa.domain.model;

import com.lucasmoraist.templa.domain.enums.DayOfWeek;

import java.time.LocalTime;
import java.util.UUID;

public record Group(
        UUID id,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime,
        int maxStudents,
        Course course
) {

}
