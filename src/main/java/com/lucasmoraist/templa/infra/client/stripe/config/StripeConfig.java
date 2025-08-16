package com.lucasmoraist.templa.infra.client.stripe.config;

import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${secrets.stripe.secret-key}")
    private String stripeSecretKey;

    @Bean
    public String stripe() {
        return Stripe.apiKey = stripeSecretKey;
    }

}
