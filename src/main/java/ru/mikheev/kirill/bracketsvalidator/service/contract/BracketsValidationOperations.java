package ru.mikheev.kirill.bracketsvalidator.service.contract;

/**
 * Service for brackets sequence validation
 */
public interface BracketsValidationOperations {

    /**
     * Validate brackets sequence in text provided that there must be text between the brackets
     *
     * @param textForValidation - text for validation
     * @return true - if brackets sequence valid, else - false
     */
    boolean validateBracketsSequenceWithText(String textForValidation);
}
