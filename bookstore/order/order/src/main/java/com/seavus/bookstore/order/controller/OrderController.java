package com.seavus.bookstore.order.controller;

import com.seavus.bookstore.order.dto.CreateOrderDto;
import com.seavus.bookstore.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/api/orders")
  @ResponseStatus(HttpStatus.CREATED)
  public void createOrder(@RequestBody CreateOrderDto dto) {
    orderService.createOrder(dto);
  }
}
