package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.CourseGateway;
import com.lucasmoraist.templa.application.usecases.teacher.GetTeacherByIdCase;
import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.domain.model.Teacher;
import com.lucasmoraist.templa.infra.db.entity.CourseEntity;
import com.lucasmoraist.templa.infra.db.entity.TeacherEntity;
import com.lucasmoraist.templa.infra.db.repository.CourseRepository;
import com.lucasmoraist.templa.infra.mapper.CourseMapper;
import com.lucasmoraist.templa.infra.mapper.TeacherMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class CourseGatewayImpl implements CourseGateway {

    private final CourseRepository courseRepository;
    private final GetTeacherByIdCase getTeacherByIdCase;

    public CourseGatewayImpl(CourseRepository courseRepository, GetTeacherByIdCase getTeacherByIdCase) {
        this.courseRepository = courseRepository;
        this.getTeacherByIdCase = getTeacherByIdCase;
    }

    @Override
    public Course create(UUID teacherId, Course course) {
        Teacher teacher = getTeacherByIdCase.execute(teacherId);
        TeacherEntity teacherEntity = TeacherMapper.toEntity(teacher);

        log.debug("Creating course: {}", course);
        CourseEntity entity = CourseMapper.toEntity(course);
        entity.setTeacher(teacherEntity);

        this.courseRepository.save(entity);
        Course createdCourse = CourseMapper.toDomain(entity);
        log.debug("Course created: {}", createdCourse);
        return createdCourse;
    }

    @Override
    public Course findById(UUID id) {
        return courseRepository.findById(id)
                .map(CourseMapper::toDomain)
                .orElseThrow(() -> {
                    log.error("Course not found with id: {}", id);
                    return new RuntimeException("Course not found");
                });
    }

}
