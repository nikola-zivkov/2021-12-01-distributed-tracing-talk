package com.seavus.bookstore.api.actuator;

import com.seavus.bookstore.api.config.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Component
@Endpoint(id = "rate-book-enabled")
public class RateBookEnabledEndpoint {

  private final WebClient webClient;

  private final Properties properties;

  @ReadOperation
  public Boolean canRate() {
    try {
      webClient.get()
          .uri(properties.getRatingUri() + "/actuator/health")
          .retrieve()
          .bodyToMono(String.class)
          .block();
      return true;
    } catch (RuntimeException e) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, Boolean.valueOf(false).toString());
    }
  }
}
