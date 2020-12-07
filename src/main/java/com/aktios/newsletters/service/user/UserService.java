package com.aktios.newsletters.service.user;

import com.aktios.newsletters.model.entity.User;

import java.util.Optional;

public interface UserService {

    /**
     * Create a User
     * @param user
     * @return
     */
    User create(User user);

    /**
     * Find a User by email
     * @param email
     * @return
     */
    Optional<User> findByEmail(String email);
}
