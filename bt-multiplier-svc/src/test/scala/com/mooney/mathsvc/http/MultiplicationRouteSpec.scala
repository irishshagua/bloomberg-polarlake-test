package com.mooney.mathsvc.http

import akka.testkit.TestProbe
import com.mooney.mathsvc.BaseHttpSpec
import com.mooney.mathsvc.service.MultiplierSvc.MultiplicationInputs

class MultiplicationRouteSpec extends BaseHttpSpec("multiplicationRoute") {

  trait Fixture {
    val multiplicationActor = TestProbe()
    val multiplicationRoute = new MultiplicationRoute(multiplicationActor.ref)
  }

  "The Multiplication Route" must "forward valid inputs to the multiplication service" in new Fixture {
    Get("/multiply/2/9") ~> multiplicationRoute.route
    multiplicationActor.expectMsg(MultiplicationInputs(2, 9))
  }

  it must "fail for invalid inputs" in new Fixture {
    Get("/multiply/foo/bar") ~> multiplicationRoute.route ~> check {
      handled shouldBe false
    }
  }
}
