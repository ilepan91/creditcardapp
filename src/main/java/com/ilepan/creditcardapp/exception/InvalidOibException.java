package com.ilepan.creditcardapp.exception;

/**
 * Custom exception class representing a scenario where a invalid OIB is provided by user.
 * This exception extends the {@link RuntimeException}, making it an unchecked exception.
 */
public class InvalidOibException extends RuntimeException {

    /**
     * Constructs a new instance of InvalidOibException with the specified error message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public InvalidOibException(String message) {
        super(message);
    }
}

