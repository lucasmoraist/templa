package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.CourseGateway;
import com.lucasmoraist.templa.application.usecases.course.SaveCourseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CourseUseCase {

    @Bean
    public SaveCourseCase saveCourseCase(CourseGateway courseGateway) {
        return new SaveCourseCase(courseGateway);
    }

}
