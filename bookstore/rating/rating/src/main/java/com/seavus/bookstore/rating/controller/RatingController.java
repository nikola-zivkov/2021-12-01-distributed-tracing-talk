package com.seavus.bookstore.rating.controller;

import com.seavus.bookstore.rating.dto.BookDto;
import com.seavus.bookstore.rating.dto.RateBookDto;
import com.seavus.bookstore.rating.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RatingController {

  private final RatingService ratingService;

  @PutMapping("/api/books/{uuid}/rating")
  public BookDto rateBook(@PathVariable String uuid, @RequestBody RateBookDto dto) {
    var book = ratingService.rateBook(uuid, dto);
    return BookDto.builder()
        .uuid(book.getUuid())
        .rating(book.getRating())
        .build();
  }
}
