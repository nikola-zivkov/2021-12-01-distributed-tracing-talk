package com.seavus.bookstore.rating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seavus.bookstore.rating.RatingSpringBootTest;
import com.seavus.bookstore.rating.dto.RateBookDto;
import com.seavus.bookstore.rating.event.BookRatedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RatingSpringBootTest
class RatingControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private OutputDestination outputDestination;

  @Test
  void rateBook() throws Exception {
    mockMvc.perform(put("/api/books/{uuid}/rating", "it").contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(RateBookDto.builder()
                .rating(5)
                .build())))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.uuid").value("it"))
        .andExpect(jsonPath("$.rating").value("5"));

    var event = objectMapper.readValue(outputDestination.receive()
        .getPayload(), BookRatedEvent.class);
    assertThat(event.getUuid()).isEqualTo("it");
    assertThat(event.getRating()).isEqualTo(5);
  }
}
