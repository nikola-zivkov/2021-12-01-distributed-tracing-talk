package com.seavus.bookstore.payment.service;

import com.seavus.bookstore.payment.dto.CreatePaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentService {

  private final Tracer tracer;

  public void createPayment(CreatePaymentDto dto) {
    log.info("Creating payment {}...", dto);
  }
}
