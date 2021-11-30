package com.seavus.bookstore.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bookstore.order")
public class Properties {

  private String paymentUri;
}
