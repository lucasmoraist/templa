package com.lucasmoraist.templa.infra.gateway;

import com.lucasmoraist.templa.application.gateway.EnrollmentGateway;
import com.lucasmoraist.templa.domain.model.Enrollment;
import com.lucasmoraist.templa.domain.model.Group;
import com.lucasmoraist.templa.domain.model.Student;
import com.lucasmoraist.templa.infra.client.stripe.service.StripeService;
import com.lucasmoraist.templa.infra.db.entity.EnrollmentEntity;
import com.lucasmoraist.templa.infra.db.repository.EnrollmentRepository;
import com.lucasmoraist.templa.infra.mapper.EnrollmentMapper;
import com.stripe.model.checkout.Session;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class EnrollmentGatewayImpl implements EnrollmentGateway {

    private final EnrollmentRepository enrollmentRepository;
    private final StripeService stripeService;

    public EnrollmentGatewayImpl(EnrollmentRepository enrollmentRepository, StripeService stripeService) {
        this.enrollmentRepository = enrollmentRepository;
        this.stripeService = stripeService;
    }

    @Override
    public void completeRegistration(Group course, Student student) {
        Enrollment enrollment = new Enrollment(null, student, course, null);
        EnrollmentEntity entity = EnrollmentMapper.toEntity(enrollment);
        log.info("Saving enrollment: {}", entity);

        enrollmentRepository.save(entity);
        log.info("Enrollment saved successfully: {}", entity);
    }

    @Override
    public String registerEnrollment(UUID id, Group group) {
        Session session = stripeService.createPaymentSession(id, group);
        log.debug("Stripe session created: {}", session.getUrl());
        return session.getUrl();
    }

}
