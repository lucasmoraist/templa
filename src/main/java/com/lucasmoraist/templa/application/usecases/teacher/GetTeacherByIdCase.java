package com.lucasmoraist.templa.application.usecases.teacher;

import com.lucasmoraist.templa.application.gateway.TeacherGateway;
import com.lucasmoraist.templa.domain.model.Teacher;

import java.util.UUID;

public class GetTeacherByIdCase {

    private final TeacherGateway teacherGateway;

    public GetTeacherByIdCase(TeacherGateway teacherGateway) {
        this.teacherGateway = teacherGateway;
    }

    public Teacher execute(UUID id) {
        return teacherGateway.findById(id);
    }

}
