package com.aktios.newsletters.service.subscription.implementation;

import com.aktios.newsletters.exception.ExistingSubscriptionException;
import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.repository.subscription.SubscriptionRepository;
import com.aktios.newsletters.service.subscription.SubscriptionService;
import com.aktios.newsletters.service.tag.TagService;
import com.aktios.newsletters.service.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

  private TagService tagService;
  private UserService userService;
  private SubscriptionRepository subscriptionRepository;

  public SubscriptionServiceImpl(
      TagService tagService,
      UserService userService,
      SubscriptionRepository subscriptionRepository) {
    this.tagService = tagService;
    this.userService = userService;
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public Subscription create(Subscription subscription) {

    // if User doesnt exists We create new User
    this.userService
        .findByEmail(subscription.getUser().getEmail())
        .ifPresentOrElse(
            subscription::setUser,
            () -> {
              subscription.setUser(this.userService.create(subscription.getUser()));
            });

    // Only can exists one service per user
    this.subscriptionRepository
        .findByUserId(subscription.getUser().getId())
        .ifPresent(
            s -> {
              throw new ExistingSubscriptionException("This user is already subscribed");
            });

    subscription.setTags(
        this.tagService.findByNames(
            subscription.getTags().stream().map(Tag::getName).collect(Collectors.toSet())));

    return this.subscriptionRepository.save(subscription);
  }

  @Override
  public void deleteById(Integer id) {
    this.subscriptionRepository.deleteById(id);
  }

  @Override
  public Page<Subscription> findAll(Pageable pageable) {
    return this.subscriptionRepository.findAll(pageable);
  }
}
