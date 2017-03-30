package com.mooney.gateway.operations

import com.mooney.gateway.models.MathematicalResult
import com.mooney.gateway.service.AsyncMathematicalOperations
import spock.lang.Specification

import java.util.concurrent.CompletableFuture

class MathematicalOperationsGatewayControllerSpec extends Specification {

    def "exceptions are properly represented in the defered result" () {
        given: "a mocked math service"
            def svc = Mock(AsyncMathematicalOperations)
            def cf = new CompletableFuture<Long>()
            cf.completeExceptionally(new Exception("err"))
            svc.multiply(_, _) >> cf

        and: "a math operations controller"
            def ctrl = new MathematicalOperationsGatewayController(svc)

        when: "a call is made to the svc which results in an exception"
            def result = ctrl.multiplyOperation(1L, 2L)

        then: "the defered result has the correct details"
            result.hasResult()
            result.result.getCause().message == "err"
    }

    def "valid responses from the math service will be wrapped in a response body" () {
        given: "a mocked math service"
            def svc = Mock(AsyncMathematicalOperations)
            def cf = new CompletableFuture<Long>()
            cf.complete(45L)
            svc.multiply(_, _) >> cf

        and: "a math operations controller"
            def ctrl = new MathematicalOperationsGatewayController(svc)

        when: "a call is made to the svc which results in an exception"
            def result = ctrl.multiplyOperation(1L, 2L)

        then: "the defered result has the correct details"
            result.hasResult()
            result.result == MathematicalResult.builder().result(45L).action(MathematicalResult.MathematicalOperation.MULTIPLICATION).build()
    }
}
