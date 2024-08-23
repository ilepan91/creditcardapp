package com.ilepan.creditcardapp.exception;

public class UserAlreadyExistsException extends RuntimeException{
    /**
     * Constructs a new instance of InvalidOibException with the specified error message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
