package com.seavus.bookstore.recommendation.service;

import com.seavus.bookstore.recommendation.model.Book;
import com.seavus.bookstore.recommendation.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecommendationService {

  private final BookRepository bookRepository;

  @Transactional(readOnly = true)
  public List<Book> recommendSimilarBooks(String bookUuid) {
    var referenceBook = bookRepository.findById(bookUuid)
        .orElseThrow();
    return bookRepository.findByGenre(referenceBook.getGenre())
        .stream()
        .filter(book -> !book.getUuid()
            .equals(bookUuid))
        .sorted((b1, b2) -> Integer.compare(b2.getRating(), b1.getRating()))
        .collect(Collectors.toList());
  }
}
