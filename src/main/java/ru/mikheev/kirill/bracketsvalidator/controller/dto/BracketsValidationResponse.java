package ru.mikheev.kirill.bracketsvalidator.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class BracketsValidationResponse {
    private boolean isCorrect;
}
