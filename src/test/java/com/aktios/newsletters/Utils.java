package com.aktios.newsletters;

import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.model.entity.SubscriptionPeriods;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.model.entity.User;

import java.time.LocalDate;
import java.util.Set;

public class Utils {

  public static Subscription fakeSubscription() {
    Subscription subscription = new Subscription();
    subscription.setId(111);
    subscription.setSubscriptionPeriod(SubscriptionPeriods.WEEKLY);
    subscription.setUser(fakeUser());
    subscription.setSubscribedAt(LocalDate.of(2020, 1, 1));
    Tag tag = new Tag();
    tag.setId(222);
    tag.setName("ANOTHER FAKE TAG");
    subscription.setTags(Set.of(fakeTag(), tag));

    return subscription;
  }

  public static User fakeUser() {
    User user = new User();
    user.setId(111);
    user.setEmail("test@email.com");
    user.setName("Name");
    user.setSurname("Surname");
    user.setBirthday(LocalDate.of(2000, 01, 01));

    return user;
  }

  public static Tag fakeTag() {
    Tag tag = new Tag();
    tag.setId(111);
    tag.setName("FAKE TAG");

    return tag;
  }
}
