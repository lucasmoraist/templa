package com.lucasmoraist.templa.application.usecases.student;

import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.domain.model.User;

public class RegisterStudentCase {

    private final StudentGateway studentGateway;

    public RegisterStudentCase(StudentGateway studentGateway) {
        this.studentGateway = studentGateway;
    }

    public Student execute(String name, User user) {
        Student student = new Student(null, name, user);
        return studentGateway.save(student);
    }

}
