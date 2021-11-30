package com.seavus.bookstore.rating.service;

import com.seavus.bookstore.rating.dto.RateBookDto;
import com.seavus.bookstore.rating.event.BookRatedEvent;
import com.seavus.bookstore.rating.model.Book;
import com.seavus.bookstore.rating.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class RatingService {

  private final BookRepository bookRepository;

  private final StreamBridge streamBridge;

  @Transactional
  public Book rateBook(String uuid, RateBookDto dto) {
    log.info("Rating book {} with {}...", uuid, dto);

    var book = bookRepository.findById(uuid)
        .orElseThrow();
    book.setRating(dto.getRating());

    streamBridge.send(BookRatedEvent.class.getSimpleName(),
        BookRatedEvent.builder()
            .uuid(book.getUuid())
            .rating(book.getRating())
            .build());

    return book;
  }
}
