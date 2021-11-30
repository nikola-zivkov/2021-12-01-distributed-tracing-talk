package com.seavus.bookstore.catalog.config;

import com.seavus.bookstore.catalog.model.Book;
import com.seavus.bookstore.catalog.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Component
public class Seeder {

  private final BookRepository bookRepository;

  @EventListener(ApplicationReadyEvent.class)
  @Transactional
  void seed() {
    bookRepository.findById("it").ifPresentOrElse(book -> {}, () -> {
      var it = new Book("It", "Stephen King", "Horror");
      it.assignUuid("it");
      bookRepository.save(it);
    });

    bookRepository.findById("the-shining").ifPresentOrElse(book -> {}, () -> {
      var theShining = new Book("The Shining", "Stephen King", "Horror");
      theShining.assignUuid("the-shining");
      bookRepository.save(theShining);
    });

    bookRepository.findById("misery").ifPresentOrElse(book -> {}, () -> {
      var misery = new Book("Misery", "Stephen King", "Horror");
      misery.assignUuid("misery");
      bookRepository.save(misery);
    });

    bookRepository.findById("dune").ifPresentOrElse(book -> {}, () -> {
      var dune = new Book("Dune", "Frank Herbert", "Science fiction");
      dune.assignUuid("dune");
      bookRepository.save(dune);
    });

    bookRepository.findById("solaris").ifPresentOrElse(book -> {}, () -> {
      var solaris = new Book("Solaris", "Stanislaw Lem", "Science fiction");
      solaris.assignUuid("solaris");
      bookRepository.save(solaris);
    });

    bookRepository.findById("foundation").ifPresentOrElse(book -> {}, () -> {
      var foundation = new Book("Foundation", "Isaac Asimov", "Science fiction");
      foundation.assignUuid("foundation");
      bookRepository.save(foundation);
    });
  }
}
