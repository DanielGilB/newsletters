package com.aktios.newsletters.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Subscription {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Enumerated(EnumType.ORDINAL)
  private SubscriptionPeriods subscriptionPeriod;

  @OneToOne(optional = false)
  private User user;

  // TODO: created_at

  // @OneToMany(mappedBy = "subscription", fetch = FetchType.LAZY)
  // private Set<Tag> tags = new HashSet<>();
}
