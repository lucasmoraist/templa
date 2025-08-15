package com.lucasmoraist.templa.infra.web.routes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/api/v1/enrollment")
public interface EnrollmentRoutes {

    @PostMapping("/group/{groupId}")
    ResponseEntity<Void> enroll(@RequestHeader("Authorization") String authorization, @PathVariable UUID groupId);

}
