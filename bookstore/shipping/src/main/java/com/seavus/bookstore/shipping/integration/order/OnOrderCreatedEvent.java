package com.seavus.bookstore.shipping.integration.order;

import com.seavus.bookstore.order.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
@Component
public class OnOrderCreatedEvent implements Consumer<OrderCreatedEvent> {

  @Override
  public void accept(OrderCreatedEvent event) {
    log.info("Consuming {}...", event);
  }
}
