package com.aktios.newsletters.controller.newsletters;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NewslettersController {

  @GetMapping("/")
  public String index() {

    return "HOLA";
  }
}
