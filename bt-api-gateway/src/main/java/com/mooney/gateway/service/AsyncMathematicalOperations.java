package com.mooney.gateway.service;

import java.util.concurrent.CompletableFuture;

public interface AsyncMathematicalOperations {

  CompletableFuture<Long> multiply(Long multiplicand, Long multiplier);
}
