package com.aktios.newsletters.model.dto;

import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class UserDTO {
  @NotEmpty @Email private String email;
  @NotEmpty private String name;
  @NotEmpty private String surname;

  @Temporal(TemporalType.DATE)
  private LocalDate birthday;
}
