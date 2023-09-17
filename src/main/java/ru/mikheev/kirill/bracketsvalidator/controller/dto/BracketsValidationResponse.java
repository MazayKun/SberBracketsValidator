package ru.mikheev.kirill.bracketsvalidator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Success brackets sequence validation response
 */
@ToString
@AllArgsConstructor
public class BracketsValidationResponse {
    /**
     * Result of brackets sequence validation
     */
    private boolean isCorrect;

    /**
     * Return private field isCorrect value with correct name
     * @return isCorrect field value
     */
    public boolean getIsCorrect() {
        return isCorrect;
    }
}
