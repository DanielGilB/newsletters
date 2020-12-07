package com.aktios.newsletters.unit.subscription;


import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.model.entity.SubscriptionPeriods;
import com.aktios.newsletters.model.entity.Tag;
import com.aktios.newsletters.model.entity.User;
import com.aktios.newsletters.repository.subscription.SubscriptionRepository;
import com.aktios.newsletters.repository.user.UserRepository;
import com.aktios.newsletters.service.subscription.SubscriptionService;
import com.aktios.newsletters.service.subscription.implementation.SubscriptionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
public class SubscriptionServiceTest {
/*
    @MockBean
    private SubscriptionRepository subscriptionRepository;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private SubscriptionService subscriptionService = new SubscriptionServiceImpl(subscriptionRepository);


    @Test
    void given_suscription_whenCreateSuscription_thenShouldCreateSuscription() {

        Subscription subscription = fakeSubscription();

        subscriptionService.create(subscription);

        Optional<Subscription> subscriptor = subscriptionRepository.findById(subscription.getId());
        Assertions.assertTrue(subscriptor.isPresent());
    }





    private Subscription fakeSubscription(){
        Subscription subscription = new Subscription();
        subscription.setId(1);
        subscription.setSubscriptionPeriod(SubscriptionPeriods.WEEKLY);
        subscription.setTags(Set.of(fakeTag()));
        subscription.setUser(fakeUser());

        return subscription;
    }

    private User fakeUser(){
        User user = new User();
        user.setId(1);
        user.setEmail("test@email.com");
        user.setName("Name");
        user.setSurname("Surname");
        user.setBirthday(LocalDate.of(2000, 01 ,01));

        return user;
    }

    private Tag fakeTag(){
        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("FAKE TAG");

        return tag;
    }

 */
}
