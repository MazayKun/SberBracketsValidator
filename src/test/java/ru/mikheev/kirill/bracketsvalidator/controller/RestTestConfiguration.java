package ru.mikheev.kirill.bracketsvalidator.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

@Profile("rest-test")
@Configuration
public class RestTestConfiguration {

    @Bean
    public BracketsValidationOperations bracketsValidationOperations() {
        return Mockito.mock(BracketsValidationOperations.class);
    }
}
