package com.lucasmoraist.templa.infra.web.request.student;

import com.lucasmoraist.templa.domain.enums.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateStudentRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 3, max = 255, message = "Name is invalid")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        @NotNull(message = "Role is required")
        Roles role
) {

}
