package com.lucasmoraist.templa.infra.db.repository;

import com.lucasmoraist.templa.infra.db.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {

}
