package com.seavus.bookstore.recommendation.service;

import com.seavus.bookstore.rating.event.BookRatedEvent;
import com.seavus.bookstore.recommendation.model.Book;
import com.seavus.bookstore.recommendation.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

  private final BookRepository bookRepository;

  @Transactional
  public Book updateRating(BookRatedEvent event) {
    var book = bookRepository.findById(event.getUuid())
        .orElseThrow();
    book.setRating(event.getRating());
    return book;
  }
}
