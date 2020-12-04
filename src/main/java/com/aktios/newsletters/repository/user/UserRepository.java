package com.aktios.newsletters.repository.user;

import com.aktios.newsletters.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}
