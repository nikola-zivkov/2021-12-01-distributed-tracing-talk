package com.seavus.bookstore.catalog.model;

import com.seavus.bookstore.model.JpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "catalog_book")
public class Book extends JpaEntity {

  public Book(String title, String author, String genre) {
    this.title = title;
    this.author = author;
    this.genre = genre;
  }

  @Getter
  private String title;

  @Getter
  private String author;

  @Getter
  private String genre;
}
