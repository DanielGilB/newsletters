package com.aktios.newsletters.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TagDTO {
  @NotEmpty private String name;
}
