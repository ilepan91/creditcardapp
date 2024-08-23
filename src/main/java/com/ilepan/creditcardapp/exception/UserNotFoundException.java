package com.ilepan.creditcardapp.exception;

/**
 * Custom exception class representing a scenario where User was not found.
 * This exception extends the {@link RuntimeException}, making it an unchecked exception.
 */
public class UserNotFoundException extends  RuntimeException{
    /**
     * Constructs a new instance of UserNotFoundException with the specified error message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }

}
