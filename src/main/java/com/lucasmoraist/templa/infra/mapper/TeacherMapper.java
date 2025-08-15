package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.infra.db.entity.TeacherEntity;
import com.lucasmoraist.templa.infra.web.response.teacher.TeacherResponse;

public final class TeacherMapper {

    public static Teacher toDomain(TeacherEntity entity) {
        return new Teacher(
                entity.getId(),
                entity.getName(),
                UserMapper.toDomain(entity.getUser())
        );
    }

    public static TeacherEntity toEntity(Teacher teacher) {
        return new TeacherEntity(
                teacher.id(),
                teacher.name(),
                UserMapper.toEntity(teacher.user())
        );
    }

    public static TeacherResponse toResponse(Teacher teacher) {
        return new TeacherResponse(
                teacher.id().toString(),
                teacher.name(),
                teacher.user().email(),
                teacher.user().role().getRole()
        );
    }

}
