package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;

import java.util.UUID;

public interface EnrollmentGateway {

    void completeRegistration(Group course, Student student);

    String registerEnrollment(UUID id, Group group);

}
