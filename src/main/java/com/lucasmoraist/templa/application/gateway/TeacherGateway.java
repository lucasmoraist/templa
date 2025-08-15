package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Teacher;

import java.util.UUID;

public interface TeacherGateway {

    Teacher save(Teacher teacher);
    Teacher findById(UUID id);

}
