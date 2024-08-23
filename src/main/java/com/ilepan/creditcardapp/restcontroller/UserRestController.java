package com.ilepan.creditcardapp.restcontroller;

import com.ilepan.creditcardapp.entity.User;
import com.ilepan.creditcardapp.exception.InvalidOibException;
import com.ilepan.creditcardapp.exception.UserAlreadyExistsException;
import com.ilepan.creditcardapp.exception.UserNotFoundException;
import com.ilepan.creditcardapp.service.UserService;
import com.ilepan.creditcardapp.validator.OibValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Optional;

/**
 * REST controller for handling CRUD operations related to User entities.
 * Exposes endpoints for retrieving, adding, updating, and deleting users.
 */
@RestController
@RequestMapping("/api")
public class UserRestController {

    /**
     * Service layer dependency for managing User entities.
     */
    private UserService userService;

     /**
     * Validator object used for checking OIB values.
     */
    private OibValidator oibValidator;

    /**
     * Constructor for creating a new instance of UserRestController.
     *
     * @param theUserService The UserService dependency injected by Spring.
      * @param theOibValidator The OibValidator dependency injected by Spring.
     */
     @Autowired
     public UserRestController(UserService theUserService, OibValidator theOibValidator) {
        userService = theUserService;
        oibValidator = theOibValidator;
     }

    /**
     * Retrieves a specific user based on the provided OIB.
     *
     * @param userOib The unique identifier of the user.
     * @return ResponseEntity containing the user if found, or a 404 Not Found status if not found.
     * @throws InvalidOibException   if the provided OIB is invalid.
     * @throws UserNotFoundException if the user with the provided OIB was not found.
     */
    @GetMapping("/user/{userOib}")
    public ResponseEntity<User> getUser(@PathVariable String userOib) {
        oibValidator.validateOIB(userOib);
        User theUser = userService
                .findByOib(userOib)
                .orElseThrow(() -> new UserNotFoundException("The user with OIB " + userOib + " not found!"));
        return ResponseEntity.ok(theUser);
    }

    /**
     * Adds a new User to the database.
     *
     * @param theUser The User object to be added.
     * @return ResponseEntity containing the added User entity.
     */
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody @Valid User theUser) {
        oibValidator.validateOIB(theUser.getOib());
        Optional<User> existingUser = userService.findByOib(theUser.getOib());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with OIB: " + theUser.getOib() + " already exists.");
        }
        // if user passes an id in json, id should be set to 0, to force a 'save' of a new User (instead of update)
        theUser.setId(0);
        User dbUser = userService.save(theUser);
        return new ResponseEntity<>(dbUser, HttpStatus.CREATED);
    }

    /**
     * Deletes a specific User based on the provided OIB.
     *
     * @param userOib The unique identifier of the user to be deleted.
     * @return ResponseEntity with no content if the deletion was successful.
     * @throws InvalidOibException   if the provided OIB is invalid.
     * @throws UserNotFoundException if the user with the specified OIB is not found.
     */
    @DeleteMapping("/user/{userOib}")
    public ResponseEntity<String> deleteUser(@PathVariable String userOib) {
        oibValidator.validateOIB(userOib);
        Optional<User> tempUser = userService.findByOib(userOib);
        if (tempUser.isEmpty()) {
            throw new UserNotFoundException("User with OIB: " + userOib + " not found.");
        }
        userService.deleteByOib(userOib);
        String responseMessage = "User with OIB: " + userOib + " has been deleted.";
        return ResponseEntity.ok(responseMessage);
    }

    /**
     * Generates a file based on the provided OIB.
     *
     * @param userOib The unique identifier of the user for whom the file is generated.
     * @return ResponseEntity containing the filename if successful, or an error message if failed.
     */
   @GetMapping("/generate/{userOib}")
    public ResponseEntity<String> generateFile(@PathVariable String userOib) throws FileAlreadyExistsException {
       oibValidator.validateOIB(userOib);
       User theUser = userService
               .findByOib(userOib)
               .orElseThrow(() -> new UserNotFoundException("The user with OIB " + userOib + " not found!"));

       if (theUser.getFileName() != null && !theUser.getFileName().isEmpty()) {
           throw new FileAlreadyExistsException(
                   "Active file already exists with file name: " + theUser.getFileName());
       }
       try {
           String filename = userService.generateFile(theUser);
           theUser.setFileName(filename);
           userService.save(theUser);
           return ResponseEntity.ok("File: " + filename + " saved in the working app directory!");
       } catch (IOException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File generation failed due to an I/O error.");
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File generation failed due to an unexpected error.");
       }
   }
}
