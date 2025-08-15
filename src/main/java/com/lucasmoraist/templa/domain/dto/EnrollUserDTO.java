package com.lucasmoraist.templa.domain.dto;

import java.util.UUID;

public record EnrollUserDTO(
        UUID groupId,
        UUID studentId
) {

}
