package com.aktios.newsletters.model.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
public class SubscriptionDTO {
  @NotEmpty // @Contraints with enum
  private String subscriptionPeriod;

  @NotNull
  private @Valid UserDTO user;

  @NotEmpty
  private Set<@Valid TagDTO> tags = new HashSet<>();
}
