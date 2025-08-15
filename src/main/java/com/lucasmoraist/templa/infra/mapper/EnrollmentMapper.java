package com.lucasmoraist.templa.infra.mapper;

import com.lucasmoraist.templa.domain.model.Enrollment;
import com.lucasmoraist.templa.infra.db.entity.EnrollmentEntity;

public final class EnrollmentMapper {

    public static Enrollment toDomain(EnrollmentEntity enrollmentEntity) {
        return new Enrollment(
                enrollmentEntity.getId(),
                StudentMapper.toDomain(enrollmentEntity.getStudent()),
                GroupMapper.toDomain(enrollmentEntity.getGroup()),
                enrollmentEntity.getEnrollmentDate()
        );
    }

    public static EnrollmentEntity toEntity(Enrollment enrollment) {
        return new EnrollmentEntity(
                enrollment.id(),
                StudentMapper.toEntity(enrollment.student()),
                GroupMapper.toEntity(enrollment.group()),
                enrollment.enrollmentDate()
        );
    }

}
