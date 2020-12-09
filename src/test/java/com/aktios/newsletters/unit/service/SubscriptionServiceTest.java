package com.aktios.newsletters.unit.service;

import com.aktios.newsletters.Utils;
import com.aktios.newsletters.exception.ExistingSubscriptionException;
import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.model.entity.User;
import com.aktios.newsletters.repository.subscription.SubscriptionRepository;
import com.aktios.newsletters.service.subscription.SubscriptionService;
import com.aktios.newsletters.service.subscription.implementation.SubscriptionServiceImpl;
import com.aktios.newsletters.service.tag.TagService;
import com.aktios.newsletters.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SubscriptionServiceTest {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_PAGE_SIZE = 5;

  @Mock private SubscriptionRepository subscriptionRepository;

  @Mock private TagService tagService;

  @Mock private UserService userService;

  @InjectMocks
  private SubscriptionService subscriptionService =
      new SubscriptionServiceImpl(tagService, userService, subscriptionRepository);

  @Test
  void givenSubscriptionId_whenDelete_thenShouldDeleteSubscription() {
    Subscription subscription = Utils.fakeSubscription();

    doNothing().when(subscriptionRepository).deleteById(subscription.getId());
    subscriptionService.deleteById(subscription.getId());

    verify(subscriptionRepository, times(1)).deleteById(subscription.getId());
  }

  @Test
  void givePage_whenFindAll_thenShouldReturnPageSubscriptions() {

    List<Subscription> expectedSubscription = new ArrayList<>(List.of(Utils.fakeSubscription()));
    Page page = new PageImpl(expectedSubscription);

    Pageable pageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);

    when(subscriptionRepository.findAll(pageable)).thenReturn(page);
    Page subscriptions = subscriptionService.findAll(pageable);

    verify(subscriptionRepository, times(1)).findAll(pageable);
    Assertions.assertEquals(subscriptions.getTotalElements(), 1);
    Assertions.assertEquals(expectedSubscription, subscriptions.getContent());
  }

  @Test
  void givenSubscription_whenCreate_thenShouldCreateSubscription() {
    Subscription subscription = Utils.fakeSubscription();
    Set<String> tagNames =
        subscription.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

    mockSubscriptionDependencies(subscription);
    Subscription expectedSubscription = subscriptionService.create(subscription);

    verify(userService, times(1)).findByEmail(subscription.getUser().getEmail());
    verify(tagService, times(1)).findByNames(tagNames);
    verify(subscriptionRepository, times(1)).save(subscription);

    Assertions.assertEquals(expectedSubscription, subscription);
  }

  @Test
  void givenSubscriptionWithNewUser_whenCreate_thenShouldCreateUserAndSubscription() {
    Subscription subscription = Utils.fakeSubscription();
    Set<String> tagNames =
        subscription.getTags().stream().map(Tag::getName).collect(Collectors.toSet());
    User newUser = new User();
    newUser.setId(999);
    newUser.setName("new name");
    newUser.setSurname("new sur");
    newUser.setBirthday(LocalDate.of(2020, 1, 1));
    newUser.setEmail("newuser@email.com");
    subscription.setUser(newUser);

    mockSubscriptionDependencies(subscription);
    when(userService.findByEmail(subscription.getUser().getEmail())).thenReturn(Optional.empty());
    when(userService.create(newUser)).thenReturn(newUser);

    Subscription expectedSubscription = subscriptionService.create(subscription);

    verify(userService, times(1)).findByEmail(subscription.getUser().getEmail());
    verify(userService, times(1)).create(newUser);
    verify(tagService, times(1)).findByNames(tagNames);
    verify(subscriptionRepository, times(1)).save(subscription);

    Assertions.assertEquals(expectedSubscription, subscription);
  }

  @Test
  void
      givenSubscriptionWithUserAlreadySubscribed_whenCreate_thenShouldThrowExistingSubscriptionException() {
    Subscription subscription = Utils.fakeSubscription();

    when(userService.findByEmail(subscription.getUser().getEmail()))
        .thenReturn(Optional.of(subscription.getUser()));
    when(subscriptionRepository.findByUserId(subscription.getUser().getId()))
        .thenReturn(Optional.of(mock(Subscription.class)));

    ExistingSubscriptionException exception =
        Assertions.assertThrows(
            ExistingSubscriptionException.class,
            () -> {
              subscriptionService.create(subscription);
            });
    Assertions.assertEquals("This user is already subscribed", exception.getMessage());
  }

  void mockSubscriptionDependencies(Subscription subscription) {
    Set<String> tagNames =
        subscription.getTags().stream().map(Tag::getName).collect(Collectors.toSet());

    when(userService.findByEmail(subscription.getUser().getEmail()))
        .thenReturn(Optional.of(subscription.getUser()));
    when(subscriptionRepository.findByUserId(subscription.getUser().getId()))
        .thenReturn(Optional.empty());
    when(tagService.findByNames(tagNames)).thenReturn(subscription.getTags());
    when(subscriptionRepository.save(subscription)).thenReturn(subscription);
  }
}
