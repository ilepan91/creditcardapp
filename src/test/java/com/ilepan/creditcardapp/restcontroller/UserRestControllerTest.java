package com.ilepan.creditcardapp.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ilepan.creditcardapp.dao.UserRepository;
import com.ilepan.creditcardapp.entity.User;
import com.ilepan.creditcardapp.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@TestPropertySource("/application-test.properties")
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class UserRestControllerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testGetUserRequest() throws Exception {
        User user = setupUser();
        entityManager.persist(user);
        entityManager.flush();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{userOib}", user.getOib()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.oib").value(user.getOib()));
    }

    @Test
    void testAddUserRequest() throws Exception {
        User user = setupUser();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(user.getFirstName()));
    }

    @Test
    void testAddUserAlreadyExist() throws Exception {
        User user = setupUser();
        entityManager.persist(user);
        entityManager.flush();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/user")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User with OIB: " + user.getOib() + " already exists."));
    }


    @Test
    void testDeleteUser() throws Exception {
        User user = setupUser();
        entityManager.persist(user);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{userOib}", user.getOib()))
                .andExpect(status().isOk());

        Optional<User> deletedUser = userService.findByOib(user.getOib());
        assertFalse(deletedUser.isPresent(), "User should be deleted from the database");
    }

    @Test
    void testDeleteUserWhenUserNotFound() throws Exception {
        String userOib = "17748241351";
        String expectedResponseMessage = "User with OIB: " + userOib + " not found.";

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{userOib}", userOib))
                .andExpect(status().isNotFound())
                .andExpect(content().string(expectedResponseMessage));
    }


    @Test
    void testGenerateFileSuccess() throws Exception {
        User user = setupUser();
        entityManager.persist(user);
        entityManager.flush();

        when(userRepository.findByOib(anyString())).thenReturn(Optional.of(user));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/{userOib}", user.getOib()))
                .andExpect(status().isOk())
                .andExpect(content().string("File: " + user.getFileName() + " saved in the working app directory!"));
    }

    @Test
    void testGenerateFileUserNotFound() throws Exception {
        String userOib = "18267724209";
        when(userRepository.findByOib(userOib)).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/{userOib}", userOib))
                .andExpect(status().isNotFound())
                .andExpect(content().string("The user with OIB 18267724209 not found!"));
    }

    @Test
    void testGenerateFileFileAlreadyExists() throws Exception {
        User user = setupUser();
        user.setFileName("existingFile.txt");
        entityManager.persist(user);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/generate/{userOib}", user.getOib()))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(
                        "An unexpected error occurred: Active file already exists with file name: existingFile.txt"));
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
