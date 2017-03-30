package com.mooney.gateway.operations;

import com.codahale.metrics.annotation.Metered;
import com.mooney.gateway.models.MathematicalResult;
import com.mooney.gateway.service.AsyncMathematicalOperations;
import com.mooney.gateway.service.BackendServerTimedOutCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.async.DeferredResult;

import java.net.SocketTimeoutException;

@RestController
public class MathematicalOperationsGatewayController {

  private static final Logger LOGGER = LoggerFactory.getLogger(MathematicalOperationsGatewayController.class);

  private AsyncMathematicalOperations asyncMathematicalOperations;

  @Autowired
  public MathematicalOperationsGatewayController(AsyncMathematicalOperations asyncMathematicalOperations) {
    this.asyncMathematicalOperations = asyncMathematicalOperations;
  }

  @Metered
  @RequestMapping(
      value = "/multiply/{multiplicand}/{multiplier}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public DeferredResult<MathematicalResult> multiplyOperation(
      @PathVariable("multiplicand") Long multiplicand,
      @PathVariable("multiplier") Long multiplier
  ) {
    LOGGER.debug("multiply request for {} * {}", multiplicand, multiplier);
    DeferredResult<MathematicalResult> result = new DeferredResult<>();
    asyncMathematicalOperations
        .multiply(multiplicand, multiplier)
        .thenApply(res -> result.setResult(MathematicalResult.builder()
            .result(res)
            .action(MathematicalResult.MathematicalOperation.MULTIPLICATION)
            .build())
        ).exceptionally(ex -> {
          LOGGER.error("Issues when trying to contact backend multiplication service", ex);
          return result.setErrorResult(mapErrToResponseException(ex));
    });
    return result;
  }

  private Throwable mapErrToResponseException(Throwable t) {
    if (t.getCause().getCause() instanceof SocketTimeoutException) {
      return new BackendServerTimedOutCall();
    } else if (t.getCause() instanceof HttpStatusCodeException) {
      return t.getCause();
    } else {
      return t;
    }
  }
}
