package com.ilepan.creditcardapp.service;

import com.ilepan.creditcardapp.constants.StatusEnum;
import com.ilepan.creditcardapp.dao.UserRepository;
import com.ilepan.creditcardapp.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void findByOib() {
        User expectedUser = setupUser();
        when(userRepository.findByOib(expectedUser.getOib())).thenReturn(Optional.of(expectedUser));

        Optional<User> actualUser = userService.findByOib(expectedUser.getOib());

        assertEquals(expectedUser, actualUser.orElse(null));
        verify(userRepository, times(1)).findByOib(expectedUser.getOib());
    }

    @Test
    void save() {
        User userToSave = setupUser();
        when(userRepository.save(userToSave)).thenReturn(userToSave);

        User savedUser = userService.save(userToSave);

        assertEquals(userToSave, savedUser);
        verify(userRepository, times(1)).save(userToSave);
    }

    @Test
    void deleteByOib() {
        String testOib = "17748241351";
        userService.deleteByOib(testOib);
        verify(userRepository, times(1)).deleteByOib(testOib);
    }

    @Test
    void generateFile() throws IOException {
        User user = setupUser();
        userService.generateFile(user);

        assertEquals(String.format("%s,%s,%s,%s", user.getFirstName(), user.getLastName(), user.getOib(),
                StatusEnum.retrieveStatusValue(user.getStatus()).toString()), "Darko,Kozul,17748241351,INACTIVE");
    }

    /**
     * Creates and configures a new user object with sample data.
     *
     * @return A User object initialized with sample data.
     */
    private User setupUser(){
        User user = new User();
        user.setFirstName("Darko");
        user.setLastName("Kozul");
        user.setOib("17748241351");
        user.setStatus("0");
        return user;
    }
}