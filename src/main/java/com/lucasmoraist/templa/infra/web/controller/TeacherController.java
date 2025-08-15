package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.teacher.GetTeacherByIdCase;
import com.lucasmoraist.templa.application.usecases.teacher.RegisterTeacherCase;
import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.domain.model.User;
import com.lucasmoraist.templa.infra.mapper.TeacherMapper;
import com.lucasmoraist.templa.infra.web.request.teacher.CreateTeacherRequest;
import com.lucasmoraist.templa.infra.web.routes.TeacherRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
public class TeacherController implements TeacherRoutes {

    private final RegisterTeacherCase registerTeacherCase;
    private final GetTeacherByIdCase getTeacherByIdCase;

    public TeacherController(RegisterTeacherCase registerTeacherCase, GetTeacherByIdCase getTeacherByIdCase) {
        this.registerTeacherCase = registerTeacherCase;
        this.getTeacherByIdCase = getTeacherByIdCase;
    }

    @Override
    public ResponseEntity<?> create(CreateTeacherRequest request) {
        User user = new User(null, request.email(), request.password(), request.role());
        Teacher teacher = this.registerTeacherCase.execute(request.name(), user);
        Map<String, Object> response = TeacherMapper.toResponse(teacher);
        URI location = URI.create("/api/v1/teacher");
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<?> getTeacherById(UUID id) {
        Teacher teacher = this.getTeacherByIdCase.execute(id);
        Map<String, Object> response = TeacherMapper.toResponse(teacher);
        return ResponseEntity.ok(response);
    }

}
