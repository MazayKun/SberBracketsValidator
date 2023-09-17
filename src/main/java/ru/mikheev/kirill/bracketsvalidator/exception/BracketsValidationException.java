package ru.mikheev.kirill.bracketsvalidator.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Generic exception type for service
 */
@Getter
public abstract class BracketsValidationException extends RuntimeException {

    /**
     * Http status code for api response
     */
    private final HttpStatus httpStatus;

    protected BracketsValidationException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

}
