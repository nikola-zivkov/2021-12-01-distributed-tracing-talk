package com.seavus.bookstore.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.seavus.bookstore.api.ApiSpringBootTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ApiSpringBootTest
public class RecommendationControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @RegisterExtension
  static WireMockExtension recommendationService = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .build();

  @RegisterExtension
  static WireMockExtension catalogService = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .build();

  @RegisterExtension
  static WireMockExtension ratingService = WireMockExtension.newInstance()
      .options(wireMockConfig().dynamicPort())
      .build();

  @DynamicPropertySource
  static void adjustServiceUris(DynamicPropertyRegistry registry) {
    registry.add("bookstore.api.recommendation-uri",
        () -> recommendationService.getRuntimeInfo()
            .getHttpBaseUrl());
    registry.add("bookstore.api.catalog-uri",
        () -> catalogService.getRuntimeInfo()
            .getHttpBaseUrl());
    registry.add("bookstore.api.rating-uri",
        () -> ratingService.getRuntimeInfo()
            .getHttpBaseUrl());
  }

  @Test
  void recommendSimilarBooks() throws Exception {
    recommendationService.givenThat(WireMock.get("/api/books/uuid/similar")
        .willReturn(aResponse().withHeader("Content-Type", APPLICATION_JSON_VALUE)
            .withBody(objectMapper.writeValueAsString(com.seavus.bookstore.rating.dto.BookDto.builder()
                .uuid("uuid")
                .rating(5)
                .build()))));

    catalogService.givenThat(WireMock.get("/api/books/uuid")
        .willReturn(aResponse().withHeader("Content-Type", APPLICATION_JSON_VALUE)
            .withBody(objectMapper.writeValueAsString(com.seavus.bookstore.catalog.dto.BookDto.builder()
                .uuid("uuid")
                .title("title")
                .author("author")
                .genre("genre")
                .build()))));

    ratingService.givenThat(WireMock.get("/api/books/uuid")
        .willReturn(aResponse().withHeader("Content-Type", APPLICATION_JSON_VALUE)
            .withBody(objectMapper.writeValueAsString(com.seavus.bookstore.rating.dto.BookDto.builder()
                .uuid("uuid")
                .rating(5)
                .build()))));

    mockMvc.perform(get("/api/books/{uuid}/similar", "uuid"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].uuid").value("uuid"))
        .andExpect(jsonPath("$[0].title").value("title"))
        .andExpect(jsonPath("$[0].author").value("author"))
        .andExpect(jsonPath("$[0].genre").value("genre"))
        .andExpect(jsonPath("$[0].rating").value(5));
  }
}
