package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.EnrollmentGateway;
import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.application.usecases.enrollment.EnrollCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentUseCase {

    @Bean
    public EnrollCase enrollCase(
            EnrollmentGateway enrollmentGateway,
            GroupGateway groupGateway,
            StudentGateway studentGateway,
            TokenGateway tokenGateway
    ) {
        return new EnrollCase(enrollmentGateway, groupGateway, studentGateway, tokenGateway);
    }

}
