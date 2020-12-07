package com.aktios.newsletters;

import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.model.entity.SubscriptionPeriods;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.model.entity.User;
import com.aktios.newsletters.repository.subscription.SubscriptionRepository;
import com.aktios.newsletters.repository.tag.TagRepository;
import com.aktios.newsletters.repository.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public class MockSeed implements CommandLineRunner {

  public static final Boolean ENABLE_MOCK_DATA = true;

  private TagRepository tagRepository;
  private UserRepository userRepository;
  private SubscriptionRepository subscriptionRepository;

  public MockSeed(
      TagRepository tagRepository,
      UserRepository userRepository,
      SubscriptionRepository subscriptionRepository) {
    this.tagRepository = tagRepository;
    this.userRepository = userRepository;
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public void run(String... args) {

    if (ENABLE_MOCK_DATA) {
      generateMockDatas();
    }
  }

  private void generateMockDatas() {

    // Tags

    Tag tag1 = new Tag();
    tag1.setName("Java");

    Tag tag2 = new Tag();
    tag2.setName("Kotlin");

    Tag tag3 = new Tag();
    tag3.setName("PHP");

    Tag tag4 = new Tag();
    tag4.setName("Rust");

    Tag tag5 = new Tag();
    tag5.setName("Go");

    tagRepository.saveAll(Set.of(tag1, tag2, tag3, tag4, tag5));

    // Users

    User user1 = new User();
    user1.setEmail("user1@email.com");
    user1.setName("user1");
    user1.setSurname("sur1");
    user1.setBirthday(LocalDate.of(2020, 1, 1));

    User user2 = new User();
    user2.setEmail("user2@email.com");
    user2.setName("user2");
    user2.setSurname("sur2");
    user2.setBirthday(LocalDate.of(2020, 2, 2));

    User user3 = new User();
    user3.setEmail("user3@email.com");
    user3.setName("user3");
    user3.setSurname("sur3");
    user3.setBirthday(LocalDate.of(2020, 3, 3));

    User unsub1 = new User();
    unsub1.setEmail("unsub1@email.com");
    unsub1.setName("unsub1");
    unsub1.setSurname("sur1");
    unsub1.setBirthday(LocalDate.of(2020, 1, 1));

    User unsub2 = new User();
    unsub2.setEmail("unsub2@email.com");
    unsub2.setName("unsub2");
    unsub2.setSurname("sur2");
    unsub2.setBirthday(LocalDate.of(2020, 2, 2));

    User unsub3 = new User();
    unsub3.setEmail("unsub3@email.com");
    unsub3.setName("unsub3");
    unsub3.setSurname("sur3");
    unsub3.setBirthday(LocalDate.of(2020, 3, 3));

    userRepository.saveAll(List.of(user1, user2, user3, unsub1, unsub2, unsub3));

    // Subscriptions

    Subscription sub1 = new Subscription();
    sub1.setSubscriptionPeriod(SubscriptionPeriods.WEEKLY);
    sub1.setUser(user1);
    sub1.getTags().add(tag1);

    Subscription sub2 = new Subscription();
    sub2.setSubscriptionPeriod(SubscriptionPeriods.MONTHLY);
    sub2.setUser(user2);
    sub2.getTags().add(tag2);
    sub2.getTags().add(tag3);

    Subscription sub3 = new Subscription();
    sub3.setSubscriptionPeriod(SubscriptionPeriods.QUARTERLY);
    sub3.setUser(user3);
    sub3.getTags().add(tag3);
    sub3.getTags().add(tag4);
    sub3.getTags().add(tag5);

    subscriptionRepository.saveAll(List.of(sub1, sub2, sub3));
  }
}
