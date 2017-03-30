package com.mooney.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

@SpringBootApplication
@EnableAsync
@EnableMetrics
public class ApiGateway {

  public static void main(String... args) {
    SpringApplication.run(ApiGateway.class, args);
  }
}
