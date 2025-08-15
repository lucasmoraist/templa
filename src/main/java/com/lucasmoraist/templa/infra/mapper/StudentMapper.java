package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.domain.model.Enrollment;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.infra.db.entity.StudentEntity;
import com.lucasmoraist.templa.infra.web.response.enrollment.EnrollmentsStudentDetails;
import com.lucasmoraist.templa.infra.web.response.student.StudentGroupResponse;
import com.lucasmoraist.templa.infra.web.response.student.StudentResponse;
import com.lucasmoraist.templa.infra.web.response.student.StudentDetails;

import java.util.List;
import java.util.Map;

public final class StudentMapper {

    public static StudentDetails toDetails(Student student) {
        return new StudentDetails(
                student.id().toString(),
                student.name(),
                student.user().email(),
                student.user().role().getRole(),
                student.enrollments().stream()
                        .map(it -> new EnrollmentsStudentDetails(
                                it.id().toString(),
                                Map.of(
                                        "id", it.group().course().id(),
                                        "name", it.group().course().name(),
                                        "description", it.group().course().description(),
                                        "modality", it.group().course().modality(),
                                        "teacher", Map.of(
                                                "id", it.group().course().teacher().id(),
                                                "name", it.group().course().teacher().name(),
                                                "email", it.group().course().teacher().user().email()
                                        )
                                ),
                                Map.of(
                                        "id", it.group().id(),
                                        "dayOfWeek", it.group().dayOfWeek().getValue(),
                                        "startTime", it.group().startTime().toString(),
                                        "endTime", it.group().endTime().toString(),
                                        "maxStudents", it.group().maxStudents()
                                ),
                                it.enrollmentDate()
                        ))
                        .toList()
        );
    }

    public static List<StudentGroupResponse> toStudentGroupResponse(List<Enrollment> enrollment) {
        return enrollment.stream()
                .map(e -> new StudentGroupResponse(
                        e.id().toString(),
                        e.student().name(),
                        e.student().user().email(),
                        e.student().user().role().getRole()
                ))
                .toList();
    }

    public static Student toDomain(StudentEntity entity) {
        return new Student(
                entity.getId(),
                entity.getName(),
                UserMapper.toDomain(entity.getUser()),
                entity.getEnrollments().stream()
                        .map(it -> new Enrollment(
                                it.getId(),
                                null,
                                new Group(
                                        it.getGroup().getId(),
                                        it.getGroup().getDayOfWeek(),
                                        it.getGroup().getStartTime(),
                                        it.getGroup().getEndTime(),
                                        it.getGroup().getMaxStudents(),
                                        new Course(
                                                it.getGroup().getCourse().getId(),
                                                it.getGroup().getCourse().getName(),
                                                it.getGroup().getCourse().getDescription(),
                                                it.getGroup().getCourse().getModality(),
                                                new Teacher(
                                                        it.getGroup().getCourse().getTeacher().getId(),
                                                        it.getGroup().getCourse().getTeacher().getName(),
                                                        UserMapper.toDomain(it.getGroup().getCourse().getTeacher().getUser()),
                                                        List.of()
                                                ),
                                                List.of()
                                        ),
                                        null
                                ),
                                it.getEnrollmentDate()
                        ))
                        .toList()
        );
    }

    public static StudentEntity toEntity(Student student) {
        return new StudentEntity(
                student.id(),
                student.name(),
                UserMapper.toEntity(student.user()),
                List.of()
        );
    }

    public static StudentResponse toResponse(Student student) {
        return new StudentResponse(
                student.id().toString(),
                student.name(),
                student.user().email(),
                student.user().role().getRole()
        );
    }

}
