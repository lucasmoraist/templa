package com.lucasmoraist.templa.infra.web.response.enrollment;

import java.time.LocalDateTime;
import java.util.Map;

public record EnrollmentsStudentDetails(
        String id,
        Map<String, Object> course,
        Map<String, Object> group,
        LocalDateTime enrollmentDate
) {

}
