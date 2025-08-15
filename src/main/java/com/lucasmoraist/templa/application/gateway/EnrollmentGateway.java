package com.lucasmoraist.templa.application.gateway;

import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;

public interface EnrollmentGateway {

    void enroll(Group course, Student student);

}
