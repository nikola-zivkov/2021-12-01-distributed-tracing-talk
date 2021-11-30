package com.seavus.bookstore.recommendation.model;

import com.seavus.bookstore.model.JpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "recommendation_book")
public class Book extends JpaEntity {

  public Book(String genre) {
    this.genre = genre;
  }

  @Getter
  private String genre;

  @Getter
  @Setter
  private Integer rating = 0;
}
