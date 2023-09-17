package ru.mikheev.kirill.bracketsvalidator.controller.dto;

import lombok.*;

/**
 * Request for validation brackets sequence in text provided that there must be text between the brackets
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class BracketsValidationInTextRequest {
    /**
     * Text for validation
     */
    private String text;
}
