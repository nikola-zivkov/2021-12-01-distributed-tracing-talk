package com.seavus.bookstore.catalog.repository;

import com.seavus.bookstore.catalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {

}
