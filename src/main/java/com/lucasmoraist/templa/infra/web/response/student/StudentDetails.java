package com.lucasmoraist.templa.infra.web.response.student;

import com.lucasmoraist.templa.infra.web.response.enrollment.EnrollmentsStudentDetails;

import java.util.List;

public record StudentDetails(
        String id,
        String name,
        String email,
        String role,
        List<EnrollmentsStudentDetails> enrollments
) {

}
