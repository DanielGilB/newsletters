package com.aktios.newsletters;

import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.model.entity.SubscriptionPeriods;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.model.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Set;

public class Utils {

  public static Subscription fakeSubscription() {
    Subscription subscription = new Subscription();
    subscription.setId(111);
    subscription.setSubscriptionPeriod(SubscriptionPeriods.WEEKLY);
    subscription.setUser(fakeUser());
    subscription.setSubscribedAt(LocalDate.of(2020, 1, 1));
    subscription.setTags(fakeTags());

    return subscription;
  }

  public static User fakeUser() {
    User user = new User();
    user.setId(111);
    user.setEmail("test@email.com");
    user.setName("fake name");
    user.setSurname("fake surname");
    user.setBirthday(LocalDate.of(2020, 01, 01));

    return user;
  }

  public static Tag fakeTag() {
    Tag tag = new Tag();
    tag.setId(111);
    tag.setName("FAKE TAG");

    return tag;
  }

  public static Set<Tag> fakeTags() {
    Tag tag = new Tag();
    tag.setId(222);
    tag.setName("ANOTHER FAKE TAG");

    Tag tag2 = fakeTag();

    return Set.of(tag, tag2);
  }

  public static String JsonFakeSubscription() {
     return "{\n" +
             "    \"subscriptionPeriod\": \"MONTHLY\",\n" +
             "    \"user\": {\n" +
             "        \"email\": \"test@email.com\",\n" +
             "        \"name\": \"fake name\",\n" +
             "        \"surname\": \"fake surname\",\n" +
             "        \"birthday\": \"2020-01-01\"\n" +
             "    },\n" +
             "    \"tags\": [\n" +
             "        {\n" +
             "            \"name\": \"FAKE TAG\"\n" +
             "        },\n" +
             "        {\n" +
             "            \"name\": \"ANOTHER FAKE TAG\"\n" +
             "        }\n" +
             "    ]\n" +
             "}";
  }

}
