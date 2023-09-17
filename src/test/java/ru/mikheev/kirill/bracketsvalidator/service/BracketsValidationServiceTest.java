package ru.mikheev.kirill.bracketsvalidator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BracketsValidationServiceTest {

    static BracketsValidationService bracketsValidationService;

    @BeforeAll
    static void init() {
        bracketsValidationService = new BracketsValidationService();

    }

    @ParameterizedTest
    @MethodSource("testValidateBracketsSequenceWithTextParametersProvider")
    void testValidateBracketsSequenceWithText(String text, boolean expected) {
        Assertions.assertEquals(
                expected,
                bracketsValidationService.validateBracketsSequenceWithText(text),
                () -> String.format("Wrong result for \"%s\" scenario", text)
        );
    }

    private static Stream<Arguments> testValidateBracketsSequenceWithTextParametersProvider() {
        return Stream.of(
                Arguments.of("()", false),
                Arguments.of(")(", false),
                Arguments.of("(a)", true),
                Arguments.of(")a(", false),
                Arguments.of("(a(", false),
                Arguments.of(")a)", false),
                Arguments.of("(a)()", false),
                Arguments.of("(a)(a)", true),
                Arguments.of("((a))", true),
                Arguments.of("a(a)(a)", true),
                Arguments.of("(a)a(a)", true),
                Arguments.of("(a)(a)a", true),
                Arguments.of("", true),
                Arguments.of("a", true),
                Arguments.of("{a}", true),
                Arguments.of("[a]", true),
                Arguments.of("{a]", false)
        );
    }
}