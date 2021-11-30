package com.seavus.bookstore.rating.config;

import com.seavus.bookstore.rating.model.Book;
import com.seavus.bookstore.rating.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class Seeder {

  private final BookRepository bookRepository;

  @EventListener(ApplicationReadyEvent.class)
  @Transactional
  void seed() {
    bookRepository.findById("it").ifPresentOrElse(book -> {}, () -> {
      var it = new Book();
      it.assignUuid("it");
      bookRepository.save(it);
    });

    bookRepository.findById("the-shining").ifPresentOrElse(book -> {}, () -> {
      var theShining = new Book();
      theShining.assignUuid("the-shining");
      bookRepository.save(theShining);
    });

    bookRepository.findById("misery").ifPresentOrElse(book -> {}, () -> {
      var misery = new Book();
      misery.assignUuid("misery");
      bookRepository.save(misery);
    });

    bookRepository.findById("dune").ifPresentOrElse(book -> {}, () -> {
      var dune = new Book();
      dune.assignUuid("dune");
      bookRepository.save(dune);
    });

    bookRepository.findById("solaris").ifPresentOrElse(book -> {}, () -> {
      var solaris = new Book();
      solaris.assignUuid("solaris");
      bookRepository.save(solaris);
    });

    bookRepository.findById("foundation").ifPresentOrElse(book -> {}, () -> {
      var foundation = new Book();
      foundation.assignUuid("foundation");
      bookRepository.save(foundation);
    });
  }
}
