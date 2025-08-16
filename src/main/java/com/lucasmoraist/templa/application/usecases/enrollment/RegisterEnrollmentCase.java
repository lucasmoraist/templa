package com.lucasmoraist.templa.application.usecases.enrollment;

import com.lucasmoraist.templa.application.gateway.CacheGateway;
import com.lucasmoraist.templa.application.gateway.EnrollmentGateway;
import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.domain.exception.GroupFullException;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;

import java.util.Map;
import java.util.UUID;

public class RegisterEnrollmentCase {

    private final GroupGateway groupGateway;
    private final StudentGateway studentGateway;
    private final TokenGateway tokenGateway;
    private final CacheGateway cacheGateway;
    private final EnrollmentGateway enrollmentGateway;

    public RegisterEnrollmentCase(GroupGateway groupGateway, StudentGateway studentGateway, TokenGateway tokenGateway, CacheGateway cacheGateway, EnrollmentGateway enrollmentGateway) {
        this.groupGateway = groupGateway;
        this.studentGateway = studentGateway;
        this.tokenGateway = tokenGateway;
        this.cacheGateway = cacheGateway;
        this.enrollmentGateway = enrollmentGateway;
    }

    public String execute(UUID groupId, String authorization) {
        Group group = groupGateway.findById(groupId);

        if (group.studentsEnrolled().size() >= group.maxStudents()) {
            throw new GroupFullException();
        }

        String token = authorization.replace("Bearer ", "");
        String email = tokenGateway.getSubject(token);

        Student student = studentGateway.findByEmail(email);

        String stripeUrl = enrollmentGateway.registerEnrollment(student.id(), group);

        cacheGateway.save(student.id().toString(), Map.of(
                "groupId", group.id(),
                "studentId", student.id()
        ));

        return stripeUrl;
    }

}
