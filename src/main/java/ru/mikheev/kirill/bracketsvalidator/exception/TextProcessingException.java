package ru.mikheev.kirill.bracketsvalidator.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception signals that error was occurred during text for validation processing
 */
public class TextProcessingException extends BracketsValidationException {

    public TextProcessingException(Throwable cause) {
        super("Error during text processing", HttpStatus.INTERNAL_SERVER_ERROR, cause);
    }
}
