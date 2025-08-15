package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.enrollment.CompleteRegistrationCase;
import com.lucasmoraist.templa.application.usecases.enrollment.RegisterEnrollmentCase;
import com.lucasmoraist.templa.infra.web.routes.EnrollmentRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EnrollmentController implements EnrollmentRoutes {

    private final RegisterEnrollmentCase registerEnrollmentCase;
    private final CompleteRegistrationCase completeRegistrationCase;

    public EnrollmentController(RegisterEnrollmentCase registerEnrollmentCase, CompleteRegistrationCase completeRegistrationCase) {
        this.registerEnrollmentCase = registerEnrollmentCase;
        this.completeRegistrationCase = completeRegistrationCase;
    }

    @Override
    public ResponseEntity<Void> enroll(String authorization, UUID groupId) {
        this.registerEnrollmentCase.execute(groupId, authorization);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> finalise(UUID studentId) {
        this.completeRegistrationCase.execute(studentId);
        return ResponseEntity.noContent().build();
    }

}
