package com.seavus.bookstore.recommendation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seavus.bookstore.recommendation.RecommendationSpringBootTest;
import com.seavus.bookstore.recommendation.model.Book;
import com.seavus.bookstore.recommendation.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RecommendationSpringBootTest
class RecommendationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private BookRepository bookRepository;

  @Test
  void recommendSimilarBooks() throws Exception {
    var misery = bookRepository.findById("misery")
        .orElseThrow();
    misery.setRating(5);
    bookRepository.save(misery);

    var theShining = bookRepository.findById("the-shining")
        .orElseThrow();
    theShining.setRating(3);
    bookRepository.save(theShining);

    mockMvc.perform(get("/api/books/{uuid}/similar", "it"))
        .andExpect(
            status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].uuid").value("misery"))
        .andExpect(jsonPath("$[1].uuid").value("the-shining"));
  }
}
