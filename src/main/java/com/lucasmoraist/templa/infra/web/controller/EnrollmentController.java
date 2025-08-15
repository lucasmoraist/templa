package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.enrollment.EnrollCase;
import com.lucasmoraist.templa.infra.web.routes.EnrollmentRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EnrollmentController implements EnrollmentRoutes {

    private final EnrollCase enrollCase;

    public EnrollmentController(EnrollCase enrollCase) {
        this.enrollCase = enrollCase;
    }

    @Override
    public ResponseEntity<Void> enroll(String authorization, UUID groupId) {
        this.enrollCase.execute(groupId, authorization);
        return ResponseEntity.noContent().build();
    }

}
