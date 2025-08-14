package com.lucasmoraist.templa.infra.web.request.group;

import com.lucasmoraist.templa.domain.enums.DayOfWeek;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record GroupRequest(
        @NotNull(message = "Day of week is required")
        DayOfWeek dayOfWeek,
        @NotNull(message = "Time is required")
        LocalTime startTime,
        @NotNull(message = "Time is required")
        LocalTime endTime,
        @NotNull(message = "Max students is required")
        int maxStudents
) {

}
