package com.seavus.bookstore.rating.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookRatedEvent {

  private String uuid;

  private Integer rating;
}
