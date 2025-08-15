package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.application.usecases.student.GetStudentByIdCase;
import com.lucasmoraist.templa.application.usecases.student.RegisterStudentCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentUseCase {

    @Bean
    public GetStudentByIdCase getStudentByIdCase(StudentGateway studentGateway) {
        return new GetStudentByIdCase(studentGateway);
    }

    @Bean
    public RegisterStudentCase registerStudentCase(StudentGateway studentGateway) {
        return new RegisterStudentCase(studentGateway);
    }

}
