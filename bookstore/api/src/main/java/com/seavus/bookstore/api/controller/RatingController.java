package com.seavus.bookstore.api.controller;

import com.seavus.bookstore.api.config.Properties;
import com.seavus.bookstore.rating.dto.BookDto;
import com.seavus.bookstore.rating.dto.RateBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@RestController
public class RatingController {

  private final WebClient webClient;

  private final Properties properties;

  @PutMapping("/api/books/{uuid}/rating")
  public BookDto rateBook(@PathVariable String uuid, @RequestBody RateBookDto dto) {
    return webClient.put()
        .uri(properties.getRatingUri() + "/api/books/{uuid}/rating", uuid)
        .bodyValue(dto)
        .retrieve()
        .bodyToMono(BookDto.class)
        .block();
  }
}
