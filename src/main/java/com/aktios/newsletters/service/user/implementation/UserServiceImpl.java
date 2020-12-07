package com.aktios.newsletters.service.user.implementation;

import com.aktios.newsletters.model.entity.User;
import com.aktios.newsletters.repository.user.UserRepository;
import com.aktios.newsletters.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User create(User user) {
    return this.userRepository.save(user);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return this.userRepository.findByEmail(email);
  }
}
