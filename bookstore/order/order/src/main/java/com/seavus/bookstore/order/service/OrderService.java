package com.seavus.bookstore.order.service;

import brave.ScopedSpan;
import com.seavus.bookstore.order.config.Properties;
import com.seavus.bookstore.order.dto.CreateOrderDto;
import com.seavus.bookstore.order.event.OrderCreatedEvent;
import com.seavus.bookstore.payment.dto.CreatePaymentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {

  private final WebClient webClient;

  private final Properties properties;

  private final StreamBridge streamBridge;

  private final Tracer tracer;

  public void createOrder(CreateOrderDto dto) {
    log.info("Creating order {}...", dto);

//    addLatency();
//    var scopedSpan = tracer.startScopedSpan("create-payment");
//    try {
//      tracer.createBaggage("payment-id", "123");
//      scopedSpan.tag("payment-id", "123");
//      scopedSpan.event("before-payment");
      createPayment(dto);
//      scopedSpan.event("after-payment");
//    } finally {
//      scopedSpan.end();
//    }
//    addLatency();

    publishOrderCreatedEvent(dto);
  }

  private void createPayment(CreateOrderDto dto) {
//    addLatency();
    var createPaymentDto = CreatePaymentDto.builder()
        .userUuid(dto.getUserUuid())
        .amount(100f)
        .build();
    log.info("Invoking payment service with {}...", createPaymentDto);
    webClient.post()
        .uri(properties.getPaymentUri() + "/api/payments")
        .bodyValue(createPaymentDto)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }

  private void publishOrderCreatedEvent(CreateOrderDto dto) {
    streamBridge.send(OrderCreatedEvent.class.getSimpleName(),
        OrderCreatedEvent.builder()
            .userUuid(dto.getUserUuid())
            .bookUuid(dto.getBookUuid())
            .build());
  }

  private void addLatency() {
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
    }
  }
}
