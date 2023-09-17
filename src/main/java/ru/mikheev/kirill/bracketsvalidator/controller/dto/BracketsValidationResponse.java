package ru.mikheev.kirill.bracketsvalidator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Success brackets sequence validation response
 */
@Getter
@ToString
@AllArgsConstructor
public class BracketsValidationResponse {
    /**
     * Result of brackets sequence validation
     */
    private boolean isCorrect;
}
