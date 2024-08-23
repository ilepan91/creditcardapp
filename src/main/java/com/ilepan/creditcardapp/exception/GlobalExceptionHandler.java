package com.ilepan.creditcardapp.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling exceptions related to User entities in RESTful endpoints.
 * It uses the {@link RestControllerAdvice} annotation to provide centralized exception handling for all
 * controllers in the application.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@link InvalidOibException}, {@link UserAlreadyExistsException}, and
     * returns a 400 Bad Request status
     *
     * @param ex The exception type {@link UserNotFoundException}, {@link InvalidOibException}.
     * @return ResponseEntity containing the exception message.
     */
    @ExceptionHandler({
            InvalidOibException.class,
            UserAlreadyExistsException.class,
            IllegalArgumentException.class
    })
    public ResponseEntity<String> handleBadRequest(final @NotNull RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }

    /**
     * Handles {@link UserNotFoundException} and returns a 404 Not Found status.
     *
     * @param ex The UserNotFoundException instance.
     * @return ResponseEntity containing the exception message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    /**
     * Handles {@link MethodArgumentNotValidException} and returns a 400 Bad Request status.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return ResponseEntity containing the exception message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions and returns a 500 Internal Server Error status.
     *
     * @param ex The Exception instance.
     * @return ResponseEntity containing the exception message.
     */
    @ExceptionHandler({Exception.class,
            FileAlreadyExistsException.class
    })
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
