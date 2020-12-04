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
import java.util.Set;

@Component
public class MockDatas implements CommandLineRunner {

  private TagRepository tagRepository;
  private UserRepository userRepository;
  private SubscriptionRepository subscriptionRepository;

  public MockDatas(
      TagRepository tagRepository,
      UserRepository userRepository,
      SubscriptionRepository subscriptionRepository) {
    this.tagRepository = tagRepository;
    this.userRepository = userRepository;
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public void run(String... args) throws Exception {

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

    User user1 = new User();
    user1.setEmail("user1@email.com");
    user1.setName("user1");
    user1.setSurname("sur1");
    user1.setBirthday(LocalDate.of(2020, 01, 01));

    Subscription sub1 = new Subscription();
    sub1.setSubscriptionPeriod(SubscriptionPeriods.WEEKLY);
    // sub1.getTags().add(tag1);
    // user1.setSubscription(sub1);

    User user2 = new User();
    user2.setEmail("user2@email.com");
    user2.setName("user2");
    user2.setSurname("sur2");
    user2.setBirthday(LocalDate.of(2020, 02, 02));

    Subscription sub2 = new Subscription();
    sub2.setSubscriptionPeriod(SubscriptionPeriods.MONTHLY);
    sub2.setUser(user2);
    // sub2.getTags().add(tag2);
    // sub2.getTags().add(tag3);
    // user2.setSubscription(sub2);

    User user3 = new User();
    user3.setEmail("user3@email.com");
    user3.setName("user3");
    user3.setSurname("sur3");
    user3.setBirthday(LocalDate.of(2020, 03, 03));
    Subscription sub3 = new Subscription();
    sub3.setSubscriptionPeriod(SubscriptionPeriods.QUARTERLY);
    // sub3.getTags().add(tag3);
    // sub3.getTags().add(tag4);
    // sub3.getTags().add(tag5);

    // user3.setSubscription(sub3);

    tagRepository.saveAll(Set.of(tag1, tag2, tag3, tag4, tag5));
    // userRepository.saveAll(Set.of(user1, user2, user3));

    User test = new User();
    test.setEmail("test@email.com");
    test.setName("test");
    test.setSurname("test");
    test.setBirthday(LocalDate.of(2020, 03, 03));

    Subscription s = new Subscription();
    s.setSubscriptionPeriod(SubscriptionPeriods.WEEKLY);
    s.setUser(test);

    test.setSubscription(s);
    userRepository.save(test);
  }
}
