package com.aktios.newsletters.model.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Subscription {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SubscriptionPeriods subscriptionPeriod;

  @OneToOne(optional = false)
  private User user;

  @CreatedDate
  private LocalDate subscribedAt;

  @ManyToMany private Set<Tag> tags = new HashSet<>();
}
