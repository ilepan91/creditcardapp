package com.ilepan.creditcardapp.service;

import com.ilepan.creditcardapp.entity.User;
import com.ilepan.creditcardapp.exception.UserNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * User interface for managing User entities.
 * Defines methods for retrieving, saving, updating, and deleting User entities.
 */
public interface UserService {

    /**
     * Retrieves a specific User entity based on the provided OIB.
     *
     * @param theOib The unique identifier of the User entity.
     * @return The found User entity, or null if not found.
     */

    Optional<User> findByOib(String theOib);
    /**
     * Saves a new or existing User entity.
     *
     * @param theUser The User object to be saved.
     * @return The saved User entity.
     */
    User save(User theUser);


    /**
     * Deletes a specific User entity based on the provided OIB.
     *
     * @param theOib The unique identifier of the User entity to be deleted.
     */
    void deleteByOib(String theOib);

    /**
     * Generates a text file containing the details of a user identified by their OIB.
     * The file is named using the user's OIB and a timestamp to ensure uniqueness.
     *
     * @param theUser the user whose details are to be written to the file
     * @return the name of the generated file
     * @throws IOException if an I/O error occurs during file creation or writing
     * @throws UserNotFoundException if no user is found with the given OIB
     */
    String generateFile(User theUser) throws IOException;

}
