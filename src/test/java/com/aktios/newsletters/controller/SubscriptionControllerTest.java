package com.aktios.newsletters.controller;

import com.aktios.newsletters.Utils;
import com.aktios.newsletters.model.dto.SubscriptionDTO;
import com.aktios.newsletters.model.entity.Subscription;
import com.aktios.newsletters.service.subscription.SubscriptionService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class SubscriptionControllerTest {

  private static final String END_POINT = "/newsletters/subscriptions/";
  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_PAGE_SIZE = 5;

  @Autowired private MockMvc mockMvc;

  @Autowired private ModelMapper modelMapper;

  @MockBean private SubscriptionService subscriptionService;

  @Test
  void givenIdSubscriptionId_whenDelete_shouldOK() throws Exception {

    Subscription subscription = Utils.fakeSubscription();

    doNothing().when(subscriptionService).deleteById(subscription.getId());

    mockMvc
        .perform(delete(END_POINT.concat(subscription.getId().toString())))
        .andExpect(status().isOk())
        .andReturn();

    verify(subscriptionService, times(1)).deleteById(subscription.getId());
  }

  @Test
  void givenPage_whenFindAll_shoulReturnPageOfSubscriptionsDTO() throws Exception {
    List<Subscription> expectedSubscription = new ArrayList<>(List.of(Utils.fakeSubscription()));
    Page page = new PageImpl(expectedSubscription);
    Pageable pageable = PageRequest.of(DEFAULT_PAGE, DEFAULT_PAGE_SIZE);

    when(subscriptionService.findAll(pageable)).thenReturn(page);
    SubscriptionDTO expected = modelMapper.map(page.getContent().get(0), SubscriptionDTO.class);

    // TODO: refactor to easier assertion
    mockMvc
        .perform(get(END_POINT))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].subscriptionPeriod").value(expected.getSubscriptionPeriod()))
        .andExpect(jsonPath("$[0].user['email']").value(expected.getUser().getEmail()))
        .andExpect(jsonPath("$[0].user['name']").value(expected.getUser().getName()))
        .andExpect(jsonPath("$[0].user['surname']").value(expected.getUser().getSurname()))
        .andExpect(
            jsonPath("$[0].user['birthday']").value(expected.getUser().getBirthday().toString()))
        .andExpect(
            jsonPath("$[0].tags[0]['name']").value(expected.getTags().iterator().next().getName()));

    verify(subscriptionService, times(1)).findAll(pageable);
  }

  @Disabled("Disabled until fix 500 test")
  @Test
  void givenSubscription_whenCreate_shouldCreateAndReturnSubscriptionDTO() throws Exception {
    Subscription subscription = Utils.fakeSubscription();

    when(subscriptionService.create(subscription)).thenReturn(subscription);
    // SubscriptionDTO actualDTO = modelMapper.map(subscriptionService.create(subscription),
    // SubscriptionDTO.class);

    mockMvc
        .perform(
            post(END_POINT)
                .content(Utils.JsonFakeSubscription())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());

    verify(subscriptionService, times(1)).create(subscription);
  }
}
