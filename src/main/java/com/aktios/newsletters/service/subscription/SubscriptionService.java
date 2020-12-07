package com.aktios.newsletters.service.subscription;

import com.aktios.newsletters.exception.ExistingSubscriptionException;
import com.aktios.newsletters.model.entity.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubscriptionService {

  /**
   * Create a new Subscription
   *
   * @param subscription
   * @return
   * @throws ExistingSubscriptionException
   */
  Subscription create(Subscription subscription) throws ExistingSubscriptionException;

  /**
   * Delete a Subscription
   *
   * @param id
   */
  void deleteById(Integer id);

  /**
   * Get Page of Subscriptions
   *
   * @param pageable
   * @return
   */
  Page<Subscription> findAll(Pageable pageable);
}
