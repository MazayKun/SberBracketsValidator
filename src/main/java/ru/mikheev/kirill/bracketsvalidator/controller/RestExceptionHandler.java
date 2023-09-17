package ru.mikheev.kirill.bracketsvalidator.controller;

import com.sun.net.httpserver.HttpsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.mikheev.kirill.bracketsvalidator.exception.BracketsValidationException;

/**
 * Exception handler for rest controllers
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String DEFAULT_ERROR_MESSAGE = "Unknown error while request processing";

    @ExceptionHandler(value = BracketsValidationException.class)
    protected ResponseEntity<Object> handleConflict(BracketsValidationException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleExceptionInternal(
                exception,
                exception.getMessage(),
                new HttpHeaders(),
                exception.getHttpStatus(),
                request
        );
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        log.error(exception.getMessage(), exception);
        return handleExceptionInternal(
                exception,
                DEFAULT_ERROR_MESSAGE,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

}
