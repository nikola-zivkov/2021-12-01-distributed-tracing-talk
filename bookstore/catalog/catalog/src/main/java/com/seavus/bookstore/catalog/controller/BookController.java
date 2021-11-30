package com.seavus.bookstore.catalog.controller;

import com.seavus.bookstore.catalog.dto.BookDto;
import com.seavus.bookstore.catalog.model.Book;
import com.seavus.bookstore.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class BookController {

  private final BookRepository bookRepository;

  @GetMapping("/api/books")
  private List<BookDto> findBooks() {
    return bookRepository.findAll()
        .stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  @GetMapping("/api/books/{uuid}")
  private BookDto findBook(@PathVariable String uuid) {
    return map(bookRepository.findById(uuid)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
  }

  private BookDto map(Book book) {
    return BookDto.builder()
        .uuid(book.getUuid())
        .title(book.getTitle())
        .author(book.getAuthor())
        .genre(book.getGenre())
        .build();
  }
}
