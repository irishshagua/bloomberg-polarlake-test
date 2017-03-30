package com.mooney.gateway.service;

import com.codahale.metrics.annotation.Timed;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class RemoteAsyncMathematicalServiceImpl implements AsyncMathematicalOperations {

  private static final Logger LOGGER = LoggerFactory.getLogger(RemoteAsyncMathematicalServiceImpl.class);

  private final RestTemplate restTemplate;
  private final String serviceHost;
  private final Integer servicePort;

  @Autowired
  public RemoteAsyncMathematicalServiceImpl(
      RestTemplate restTemplate,
      @Value("${service.multiplier.host}") String serviceHost,
      @Value("${service.multiplier.port}") Integer servicePort) {
    this.restTemplate = restTemplate;
    this.serviceHost = serviceHost;
    this.servicePort = servicePort;
  }

  @Timed
  @Async
  public CompletableFuture<Long> multiply(@NonNull Long multiplicand, @NonNull Long multiplier) {
    String url = String.format("http://%s:%d/multiply/{multiplicand}/{multiplier}", serviceHost, servicePort);
    LOGGER.debug("Forwarding request {}: {} {}", url, multiplicand, multiplier);
    return CompletableFuture.completedFuture(Long.valueOf(restTemplate.getForObject(url, String.class, multiplicand, multiplier)));
  }
}
