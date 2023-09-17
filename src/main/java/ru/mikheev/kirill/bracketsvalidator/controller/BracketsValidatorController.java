package ru.mikheev.kirill.bracketsvalidator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mikheev.kirill.bracketsvalidator.controller.dto.BracketsValidationInTextRequest;
import ru.mikheev.kirill.bracketsvalidator.controller.dto.BracketsValidationResponse;
import ru.mikheev.kirill.bracketsvalidator.service.contract.BracketsValidationOperations;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BracketsValidatorController {

    private final BracketsValidationOperations bracketsValidationOperations;

    @PostMapping(path = "/checkBrackets")
    public BracketsValidationResponse validateBracketsSequenceGetHandler(
            @RequestBody BracketsValidationInTextRequest request
    ) {
        log.debug("Request for brackets sequence validation in \"{}\"", request.getText());
        return new BracketsValidationResponse(
                bracketsValidationOperations.validateBracketsSequenceWithText(
                        request.getText()
                )
        );
    }
}
