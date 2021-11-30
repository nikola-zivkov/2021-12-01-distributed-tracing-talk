package com.seavus.bookstore.recommendation.controller;

import com.seavus.bookstore.recommendation.dto.BookDto;
import com.seavus.bookstore.recommendation.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RecommendationController {

  private final RecommendationService recommendationService;

  @GetMapping("/api/books/{uuid}/similar")
  public List<BookDto> recommendSimilarBooks(@PathVariable("uuid") String bookUuid) {
    return recommendationService.recommendSimilarBooks(bookUuid)
        .stream()
        .map(book -> BookDto.builder()
            .uuid(book.getUuid())
            .build())
        .collect(
            Collectors.toList());
  }
}
