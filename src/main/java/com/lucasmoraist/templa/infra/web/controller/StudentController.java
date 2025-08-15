package com.lucasmoraist.templa.infra.web.controller;

import com.lucasmoraist.templa.application.usecases.student.GetStudentByIdCase;
import com.lucasmoraist.templa.application.usecases.student.RegisterStudentCase;
import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.domain.model.User;
import com.lucasmoraist.templa.infra.mapper.StudentMapper;
import com.lucasmoraist.templa.infra.web.request.student.CreateStudentRequest;
import com.lucasmoraist.templa.infra.web.routes.StudentRoutes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

@RestController
public class StudentController implements StudentRoutes {

    private final RegisterStudentCase registerStudentCase;
    private final GetStudentByIdCase getStudentByIdCase;

    public StudentController(RegisterStudentCase registerStudentCase, GetStudentByIdCase getStudentByIdCase) {
        this.registerStudentCase = registerStudentCase;
        this.getStudentByIdCase = getStudentByIdCase;
    }

    @Override
    public ResponseEntity<?> create(CreateStudentRequest request) {
        User user = new User(null, request.email(), request.password(), request.role());
        Student student = this.registerStudentCase.execute(request.name(), user);
        Map<String, Object> response = StudentMapper.toDetails(student);
        URI location = URI.create("/api/v1/student");
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<?> getStudentById(UUID id) {
        Student student = this.getStudentByIdCase.execute(id);
        Map<String, Object> response = StudentMapper.toDetails(student);
        return ResponseEntity.ok(response);
    }

}
