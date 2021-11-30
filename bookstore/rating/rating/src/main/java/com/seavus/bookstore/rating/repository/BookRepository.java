package com.seavus.bookstore.rating.repository;

import com.seavus.bookstore.rating.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
