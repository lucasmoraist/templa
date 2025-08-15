package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.infra.db.entity.StudentEntity;
import com.lucasmoraist.templa.infra.web.response.student.StudentResponse;

import java.util.List;

public final class StudentMapper {

    public static Student toDomain(StudentEntity entity) {
        return new Student(
                entity.getId(),
                entity.getName(),
                UserMapper.toDomain(entity.getUser()),
                List.of()
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
