package com.aktios.newsletters.repository.subscription;

import com.aktios.newsletters.model.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Optional<Subscription> findByUserId(Integer id);
}
