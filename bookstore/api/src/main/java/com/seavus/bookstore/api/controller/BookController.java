package com.seavus.bookstore.api.controller;

import com.seavus.bookstore.api.config.Properties;
import com.seavus.bookstore.api.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookController {

  private final Properties properties;

  private final WebClient webClient;

  @GetMapping("/api/books")
  public List<BookDto> findBooks() {
    return webClient.get()
        .uri(properties.getCatalogUri() + "/api/books")
        .retrieve()
        .bodyToFlux(com.seavus.bookstore.catalog.dto.BookDto.class)
        .map(catalogBook -> BookDto.builder()
            .uuid(catalogBook.getUuid())
            .title(catalogBook.getTitle())
            .author(catalogBook.getAuthor())
            .genre(catalogBook.getGenre())
            .build())
        .flatMap(book -> webClient.get()
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
