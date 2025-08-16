package com.lucasmoraist.templa.infra.client.stripe.service;

import com.lucasmoraist.templa.domain.model.Group;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Log4j2
@Service
public class StripeService {

    @Value("${secrets.stripe.success-url}")
    private String successUrl;
    @Value("${secrets.stripe.cancel-url}")
    private String cancelUrl;
    @Value("${secrets.stripe.expires-at}")
    private Long expiresAt;

    public Session createPaymentSession(UUID studentId, Group group) {
        try {
            SessionCreateParams params =
                    SessionCreateParams.builder()
                            .addLineItem(
                                    SessionCreateParams.LineItem.builder()
                                            .setQuantity(1L)
                                            .setPriceData(
                                                    SessionCreateParams.LineItem.PriceData.builder()
                                                            .setCurrency("brl")
                                                            .setUnitAmount(100L) // TODO: Deverá pegar o valor a partir do curso
                                                            .setProductData(
                                                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                            .setName(group.course().name())
                                                                            .setDescription(group.course().description())
                                                                            .build()
                                                            )
                                                            .build()
                                            )
                                            .build()
                            )
                            .setMode(SessionCreateParams.Mode.PAYMENT)
                            .setSuccessUrl(successUrl+studentId)
                            .setCancelUrl(cancelUrl)
                            .setExpiresAt(LocalDateTime.now().plusHours(expiresAt).toEpochSecond(ZoneOffset.UTC))
                            .build();

            return Session.create(params);
        } catch (StripeException ex) {
            log.error("Error creating Stripe payment session for studentId {} and groupId {}: {}", studentId, group.id(), ex.getMessage());
            throw new RuntimeException("Failed to create payment session", ex);
        }
    }

}
