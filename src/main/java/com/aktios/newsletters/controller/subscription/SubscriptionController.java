package com.aktios.newsletters.controller.subscription;

import com.aktios.newsletters.model.dto.SubscriptionDTO;
import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.service.subscription.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/newsletters/subscriptions")
public class SubscriptionController {

  private static final String DEFAULT_PAGE = "0";
  private static final String DEFAULT_PAGE_SIZE = "5";

  private SubscriptionService subscriptionService;

  private ModelMapper modelMapper;

  public SubscriptionController(SubscriptionService subscriptionService, ModelMapper modelMapper) {
    this.subscriptionService = subscriptionService;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public ResponseEntity<Integer> create(@RequestBody @Valid SubscriptionDTO subscriptionDTO) {
    try {
      Integer subscriptionId =
          this.subscriptionService
              .create(this.modelMapper.map(subscriptionDTO, Subscription.class))
              .getId();
      return new ResponseEntity<>(subscriptionId, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Integer id) {
    try {
      this.subscriptionService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<List<SubscriptionDTO>> findAll(
      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size) {
    try {
      List<SubscriptionDTO> subscriptionDTOS =
          this.subscriptionService.findAll(PageRequest.of(page, size)).stream()
              .map(s -> modelMapper.map(s, SubscriptionDTO.class))
              .collect(Collectors.toList());
      return new ResponseEntity<>(subscriptionDTOS, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
