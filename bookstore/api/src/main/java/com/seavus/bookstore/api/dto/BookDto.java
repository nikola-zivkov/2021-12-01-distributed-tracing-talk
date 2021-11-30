package com.seavus.bookstore.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookDto {

  private String uuid;

  private String title;

  private String author;

  private String genre;

  private Integer rating;
}
