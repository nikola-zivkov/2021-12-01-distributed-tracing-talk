package com.seavus.bookstore.rating;

import org.springframework.cloud.stream.binder.test.TestChannelBinder;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TestChannelBinderConfiguration.class)
public class TestConfig {

}
