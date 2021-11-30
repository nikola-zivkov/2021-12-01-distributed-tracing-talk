package com.seavus.bookstore.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bookstore.api")
public class Properties {

  private String catalogUri;

  private String recommendationUri;

  private String ratingUri;

  private String orderUri;
}
