package com.lucasmoraist.templa.infra.web.request.group;

import com.lucasmoraist.templa.domain.enums.DayOfWeek;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GroupRequest(
        @NotNull(message = "Day of week is required")
        DayOfWeek dayOfWeek,
        @NotBlank(message = "Time is required")
        String time,
        @NotBlank(message = "Duration is required")
        String duration,
        @NotNull(message = "Max students is required")
        int maxStudents
) {

}
