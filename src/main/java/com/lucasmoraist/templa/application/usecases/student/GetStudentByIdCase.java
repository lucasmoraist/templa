package com.lucasmoraist.templa.application.usecases.student;

import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.domain.model.Student;

import java.util.UUID;

public class GetStudentByIdCase {

    private final StudentGateway studentGateway;

    public GetStudentByIdCase(StudentGateway studentGateway) {
        this.studentGateway = studentGateway;
    }

    public Student execute(UUID id) {
        return studentGateway.findById(id);
    }

}
