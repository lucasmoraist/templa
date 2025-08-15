package com.lucasmoraist.templa.application.usecases.enrollment;

import com.lucasmoraist.templa.application.gateway.EnrollmentGateway;
import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;

import java.util.UUID;

public class EnrollCase {

    private final EnrollmentGateway enrollmentGateway;
    private final GroupGateway groupGateway;
    private final StudentGateway studentGateway;
    private final TokenGateway tokenGateway;

    public EnrollCase(EnrollmentGateway enrollmentGateway, GroupGateway groupGateway, StudentGateway studentGateway, TokenGateway tokenGateway) {
        this.enrollmentGateway = enrollmentGateway;
        this.groupGateway = groupGateway;
        this.studentGateway = studentGateway;
        this.tokenGateway = tokenGateway;
    }

    public void execute(UUID groupId, String authorization) {
        Group group = groupGateway.findById(groupId);

        if (group.studentsEnrolled().size() >= group.maxStudents()) {
            throw new IllegalStateException("Group is full");
        }

        String token = authorization.replace("Bearer ", "");
        String email = tokenGateway.getSubject(token);

        Student student = studentGateway.findByEmail(email);

        this.enrollmentGateway.enroll(group, student);
    }

}
