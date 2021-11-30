package com.seavus.bookstore.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderCreatedEvent {

  private String userUuid;

  private String bookUuid;
}
