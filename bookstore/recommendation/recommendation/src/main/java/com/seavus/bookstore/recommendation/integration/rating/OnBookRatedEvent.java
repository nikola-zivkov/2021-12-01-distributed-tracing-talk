package com.seavus.bookstore.recommendation.integration.rating;

import com.seavus.bookstore.rating.event.BookRatedEvent;
import com.seavus.bookstore.recommendation.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
@Component
public class OnBookRatedEvent implements Consumer<BookRatedEvent> {

  private final BookService bookService;

  @Override
  @Transactional
  public void accept(BookRatedEvent event) {
    log.info("Consuming {}...", event);
    bookService.updateRating(event);
  }
}
