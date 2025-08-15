package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Student;

import java.util.UUID;

public interface StudentGateway {

    Student save(Student student);
    Student findById(UUID id);

}
