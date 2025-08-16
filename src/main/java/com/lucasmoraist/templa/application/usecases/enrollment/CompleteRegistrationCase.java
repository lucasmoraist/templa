package com.lucasmoraist.templa.application.usecases.enrollment;

import com.lucasmoraist.templa.application.gateway.CacheGateway;
import com.lucasmoraist.templa.application.gateway.EnrollmentGateway;
import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.domain.dto.EnrollUserDTO;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;

import java.util.UUID;

public class CompleteRegistrationCase {

    private final EnrollmentGateway enrollmentGateway;
    private final GroupGateway groupGateway;
    private final StudentGateway studentGateway;
    private final CacheGateway cacheGateway;

    public CompleteRegistrationCase(EnrollmentGateway enrollmentGateway, GroupGateway groupGateway, StudentGateway studentGateway, CacheGateway cacheGateway) {
        this.enrollmentGateway = enrollmentGateway;
        this.groupGateway = groupGateway;
        this.studentGateway = studentGateway;
        this.cacheGateway = cacheGateway;
    }

    public void execute(UUID studentId) {
        EnrollUserDTO enrollUserDTO = this.cacheGateway.get(studentId.toString());

        Group group = groupGateway.findById(enrollUserDTO.groupId());
        Student student = studentGateway.findById(enrollUserDTO.studentId());

        // TODO: Aqui terá um web socket para saber que a pessoa completou o pagamento
        this.enrollmentGateway.completeRegistration(group, student);
        this.cacheGateway.delete(studentId.toString());
    }

}
