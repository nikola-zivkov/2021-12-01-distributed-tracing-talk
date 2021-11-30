package com.seavus.bookstore.payment.controller;

import com.seavus.bookstore.payment.dto.CreatePaymentDto;
import com.seavus.bookstore.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/api/payments")
  public void createPayment(@RequestBody CreatePaymentDto dto) {
    paymentService.createPayment(dto);
  }
}
