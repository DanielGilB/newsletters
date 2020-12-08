package com.aktios.newsletters.unit.service;

import com.aktios.newsletters.Utils;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.Mockito.*;

@SpringBootTest
public class SubscriptionServiceTest {

  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_PAGE_SIZE = 1;

  @Captor ArgumentCaptor<Tag> tagCaptor;

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

    when(userService.findByEmail(subscription.getUser().getEmail()))
        .thenReturn(Optional.of(subscription.getUser()));
    when(tagService.findByName(anyString()))
        .thenReturn(Optional.of(subscription.getTags().stream().findFirst().get()))
        .thenReturn(Optional.of(subscription.getTags().stream().skip(1).findFirst().get()));
    when(subscriptionRepository.save(subscription)).thenReturn(subscription);

    Subscription expectedSubscription = subscriptionService.create(subscription);


    verify(userService, times(1)).findByEmail(subscription.getUser().getEmail());
    //verify(tagService, times(subscription.getTags().size())).findByName(tagCaptor.capture().getName());
    verify(subscriptionRepository, times(1)).save(subscription);

    Assertions.assertEquals(expectedSubscription, subscription);
  }
}
