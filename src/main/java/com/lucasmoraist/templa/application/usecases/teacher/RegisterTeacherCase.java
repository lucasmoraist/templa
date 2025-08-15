package com.lucasmoraist.templa.application.usecases.teacher;

import com.lucasmoraist.templa.application.gateway.TeacherGateway;
import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.domain.model.User;

public class RegisterTeacherCase {

    private final TeacherGateway teacherGateway;

    public RegisterTeacherCase(TeacherGateway teacherGateway) {
        this.teacherGateway = teacherGateway;
    }

    public Teacher execute(String name, User user) {
        Teacher teacher = new Teacher(null, name, user);
        return teacherGateway.save(teacher);
    }

}
