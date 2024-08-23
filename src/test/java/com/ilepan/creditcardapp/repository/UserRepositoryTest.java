package com.ilepan.creditcardapp.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import com.ilepan.creditcardapp.dao.UserRepository;
import com.ilepan.creditcardapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired private UserRepository userRepository;

    @Test
    void testFindByOib() {
        User user = setupUser();
        entityManager.persist(user);
        entityManager.flush();

        Optional<User> foundUser = userRepository.findByOib(user.getOib());

        assertTrue(foundUser.isPresent());
        assertEquals(user.getOib(), foundUser.get().getOib());
        assertEquals(user.getFirstName(), foundUser.get().getFirstName());
        assertEquals(user.getLastName(), foundUser.get().getLastName());
        assertEquals(user.getStatus(), foundUser.get().getStatus());
    }

    @Test
    void testDeleteByOib() {
        User user = setupUser();
        entityManager.persist(user);
        entityManager.flush();

        assertTrue(userRepository.findByOib(user.getOib()).isPresent());

        userRepository.deleteByOib(user.getOib());
        entityManager.flush();

        assertFalse(userRepository.findByOib(user.getOib()).isPresent());
    }

    /**
     * Creates and configures a new user object with sample data.
     *
     * @return A User object initialized with sample data.
     */
    private User setupUser(){
        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Horvat");
        user.setOib("17748241351");
        user.setStatus("0");
        return user;
    }
}
