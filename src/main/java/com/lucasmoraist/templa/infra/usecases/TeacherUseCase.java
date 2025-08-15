package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.TeacherGateway;
import com.lucasmoraist.templa.application.usecases.teacher.GetTeacherByIdCase;
import com.lucasmoraist.templa.application.usecases.teacher.RegisterTeacherCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherUseCase {

    @Bean
    public GetTeacherByIdCase getTeacherByIdCase(TeacherGateway teacherGateway) {
        return new GetTeacherByIdCase(teacherGateway);
    }

    @Bean
    public RegisterTeacherCase registerTeacherCase(TeacherGateway teacherGateway) {
        return new RegisterTeacherCase(teacherGateway);
    }

}
