package com.seavus.bookstore.rating.controller;

import com.seavus.bookstore.rating.dto.BookDto;
import com.seavus.bookstore.rating.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@RestController
public class BookController {

  private final BookRepository bookRepository;

  @GetMapping("/api/books/{uuid}")
  public BookDto findBook(@PathVariable String uuid) {
    var book = bookRepository.findById(uuid)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    return BookDto.builder()
        .uuid(book.getUuid())
        .rating(book.getRating())
        .build();
  }
}
