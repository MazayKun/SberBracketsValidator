package ru.mikheev.kirill.bracketsvalidator.controller.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class BracketsValidationRequest {
    private String text;
}
