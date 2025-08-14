package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.CourseGateway;
import com.lucasmoraist.templa.domain.model.Course;
import com.lucasmoraist.templa.infra.db.entity.CourseEntity;
import com.lucasmoraist.templa.infra.db.repository.CourseRepository;
import com.lucasmoraist.templa.infra.mapper.CourseMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CourseGatewayImpl implements CourseGateway {

    private final CourseRepository courseRepository;

    public CourseGatewayImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course create(Course course) {
        log.debug("Creating course: {}", course);
        CourseEntity entity = CourseMapper.toEntity(course);

        this.courseRepository.save(entity);
        Course createdCourse = CourseMapper.toDomain(entity);
        log.debug("Course created: {}", createdCourse);
        return createdCourse;
    }

}
