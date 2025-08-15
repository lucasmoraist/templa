package com.lucasmoraist.templa.infra.usecases;

import com.lucasmoraist.templa.application.gateway.CacheGateway;
import com.lucasmoraist.templa.application.gateway.GroupGateway;
import com.lucasmoraist.templa.application.gateway.StudentGateway;
import com.lucasmoraist.templa.application.gateway.TokenGateway;
import com.lucasmoraist.templa.application.usecases.enrollment.RegisterEnrollmentCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentUseCase {

    @Bean
    public RegisterEnrollmentCase enrollCase(
            GroupGateway groupGateway,
            StudentGateway studentGateway,
            TokenGateway tokenGateway,
            CacheGateway cacheGateway
    ) {
        return new RegisterEnrollmentCase(groupGateway, studentGateway, tokenGateway, cacheGateway);
    }

}
