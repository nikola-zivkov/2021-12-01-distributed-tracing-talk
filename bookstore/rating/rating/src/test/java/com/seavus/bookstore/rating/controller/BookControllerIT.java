package com.seavus.bookstore.rating.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seavus.bookstore.rating.RatingSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RatingSpringBootTest
class BookControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private OutputDestination outputDestination;

  @Test
  void findBook() throws Exception {
    mockMvc.perform(get("/api/books/{uuid}", "it"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$.uuid").value("it"))
        .andExpect(jsonPath("$.rating").value(0));
  }

  @Test
  void findBook_bookDoesNotExist() throws Exception {
    mockMvc.perform(get("/api/books/{uuid}", "invalid"))
        .andExpect(status().isNotFound());
  }
}
