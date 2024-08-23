package com.ilepan.creditcardapp.service;

import com.ilepan.creditcardapp.constants.StatusEnum;
import com.ilepan.creditcardapp.dao.UserRepository;
import com.ilepan.creditcardapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * This class provides the implementation of the {@link UserService} interface.
 * It manages User entities by interacting with the data layer through the {@link UserService}.
 *
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * Logger for the UserServiceImpl class.
     * This logger is used to log information, warnings, and errors related to
     * the file writing process and other operations within this class.
     */
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * The repository for interacting with user data.
     */
    private UserRepository userRepository;

    /**
     * Constructor for creating a new instance of UserServiceImpl.
     *
     * @param theUserRepository The userRepository dependency injected by Spring
     */
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Override
    public Optional<User> findByOib(String theOib) {
        log.info("Finding User by OiB: {}", theOib);
        return userRepository.findByOib(theOib);
    }

    @Transactional
    @Override
    public User save(User theUser) {
        log.info("Saving User: {}", theUser);
        return userRepository.save(theUser);
    }

    @Transactional
    @Override
    public void deleteByOib(String theOib) {
        userRepository.deleteByOib(theOib);
        log.info("User with OIB: {} has been deleted.", theOib);
    }

    public String generateFile(User user) throws IOException {
        log.info("Generating file for User with OIB: {}", user.getOib());
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String filename =  user.getOib() + "_" + timestamp + ".txt";

        try (FileWriter writer = new FileWriter(filename)) {
            log.info("Attempting to write to file: {}", filename);
            writer.write(String.format("%s,%s,%s,%s",
                    user.getFirstName(),
                    user.getLastName(),
                    user.getOib(),
                    StatusEnum.retrieveStatusValue(user.
                            getStatus()).toString()));
        }
        log.info("File '{}' generated for User with OIB: {}", filename,  user.getOib());
        return filename;
    }
}
