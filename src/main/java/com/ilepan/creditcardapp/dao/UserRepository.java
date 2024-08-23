package com.ilepan.creditcardapp.dao;

import com.ilepan.creditcardapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link User} entities.
 * This interface extends the Spring Data JPA {@link JpaRepository},
 * providing CRUD (Create, Read, Update, Delete) operations for User entities
 * with the primary key of type {@code Integer}.
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    // Custom method to find users by OIB
    Optional<User> findByOib(String theOib);

    // Custom method to delete users by OIB
    void deleteByOib(String theOib);
}
