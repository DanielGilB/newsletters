package com.aktios.newsletters.unit.service;

import com.aktios.newsletters.Utils;
import com.aktios.newsletters.model.entity.User;
import com.aktios.newsletters.repository.user.UserRepository;
import com.aktios.newsletters.service.user.UserService;
import com.aktios.newsletters.service.user.implementation.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService = new UserServiceImpl(userRepository);

  @Test
  void givenUser_whenCreate_shouldCreateUser() {
    User user = Utils.fakeUser();

    when(userRepository.save(user)).thenReturn(user);
    userService.create(user);

    verify(userRepository, times(1)).save(user);
  }

  @Test
  void givenExistingEmail_whenFindByEmail_shouldReturnUser() {
    User user = Utils.fakeUser();

    when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
    userService.findByEmail(user.getEmail());

    verify(userRepository, times(1)).findByEmail(user.getEmail());
  }

  @Test
  void givenNotExistingEmail_whenFindByEmail_shouldReturnEmpty() {
    User user = Utils.fakeUser();
    String unexistingEmail = "unexisting@email.com";

    when(userService.findByEmail(unexistingEmail)).thenReturn(Optional.empty());
    userService.findByEmail(unexistingEmail);

    verify(userRepository, times(1)).findByEmail(unexistingEmail);
  }
}
