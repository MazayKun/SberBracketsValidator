package ru.mikheev.kirill.bracketsvalidator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mikheev.kirill.bracketsvalidator.controller.dto.BracketsValidationRequest;
import ru.mikheev.kirill.bracketsvalidator.controller.dto.BracketsValidationResponse;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

@RestController
@RequiredArgsConstructor
public class BracketsValidatorController {

    private final BracketsValidationOperations bracketsValidationOperations;

    @PostMapping(path = "/checkBrackets")
    public BracketsValidationResponse validateBracketsSequenceGetHandler(
            @RequestBody BracketsValidationRequest request
    ) {
        return new BracketsValidationResponse(
                bracketsValidationOperations.validateBracketsSequenceWithText(
                        request.getText()
                )
        );
    }
}
