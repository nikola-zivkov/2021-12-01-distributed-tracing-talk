package com.seavus.bookstore.api.controller;

import com.seavus.bookstore.api.config.Properties;
import com.seavus.bookstore.api.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RecommendationController {

  private final Properties properties;

  private final WebClient webClient;

  @GetMapping("/api/books/{uuid}/similar")
  public List<BookDto> recommendSimilarBooks(@PathVariable("uuid") String bookUuid) {
    return webClient.get()
        .uri(properties.getRecommendationUri() + "/api/books/{uuid}/similar", bookUuid)
        .retrieve()
        .bodyToFlux(com.seavus.bookstore.recommendation.dto.BookDto.class)
        .map(recommendationBook -> BookDto.builder()
            .uuid(recommendationBook.getUuid())
            .build())
        .flatMapSequential(book -> webClient.get()
            .uri(properties.getCatalogUri() + "/api/books/{uuid}", book.getUuid())
            .retrieve()
            .bodyToMono(com.seavus.bookstore.catalog.dto.BookDto.class)
            .map(catalogBook -> {
              book.setTitle(catalogBook.getTitle());
              book.setAuthor(catalogBook.getAuthor());
              book.setGenre(catalogBook.getGenre());
              return book;
            }))
        .flatMapSequential(book -> webClient.get()
            .uri(properties.getRatingUri() + "/api/books/{uuid}", book.getUuid())
            .retrieve()
            .bodyToMono(com.seavus.bookstore.rating.dto.BookDto.class)
            .map(ratingBook -> {
              book.setRating(ratingBook.getRating());
              return book;
            })
            .onErrorReturn(book))
        .collectList()
        .block();
  }
}
