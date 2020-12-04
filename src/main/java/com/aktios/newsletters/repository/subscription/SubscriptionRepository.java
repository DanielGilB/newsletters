package com.aktios.newsletters.repository.subscription;

import com.aktios.newsletters.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {}
