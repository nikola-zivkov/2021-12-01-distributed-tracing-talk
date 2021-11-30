package com.seavus.bookstore.recommendation.repository;

import com.seavus.bookstore.recommendation.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {

  List<Book> findByGenre(String genre);
}
