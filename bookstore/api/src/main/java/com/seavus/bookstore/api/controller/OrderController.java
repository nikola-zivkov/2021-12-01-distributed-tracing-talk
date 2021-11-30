package com.seavus.bookstore.api.controller;

import com.seavus.bookstore.api.config.Properties;
import com.seavus.bookstore.api.dto.CreateOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@RestController
public class OrderController {

  private final WebClient webClient;

  private final Properties properties;

  @PostMapping("/api/orders")
  @ResponseStatus(HttpStatus.CREATED)
  public void createOrder(@RequestBody CreateOrderDto dto) {
    webClient.post()
        .uri(properties.getOrderUri() + "/api/orders")
        .bodyValue(com.seavus.bookstore.order.dto.CreateOrderDto.builder()
            .bookUuid(dto.getBookUuid())
            .userUuid("user-uuid")
            .build())
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }
}
