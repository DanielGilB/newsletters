package com.aktios.newsletters.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @NotEmpty @Email private String email;
  @NotEmpty private String name;
  @NotEmpty private String surname;
  private LocalDate birthday;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Subscription subscription;
}
