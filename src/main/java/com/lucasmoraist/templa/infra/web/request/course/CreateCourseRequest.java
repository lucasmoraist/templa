package com.lucasmoraist.templa.infra.web.request.course;

import com.lucasmoraist.templa.domain.enums.Modality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCourseRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name is invalid")
        String name,
        String description,
        @NotNull(message = "Modality is required")
        Modality modality
) {

}
