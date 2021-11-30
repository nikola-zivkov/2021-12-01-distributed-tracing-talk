package com.seavus.bookstore.rating.model;

import com.seavus.bookstore.model.JpaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "rating_book")
public class Book extends JpaEntity {

  @Getter
  @Setter
  private Integer rating = 0;
}
