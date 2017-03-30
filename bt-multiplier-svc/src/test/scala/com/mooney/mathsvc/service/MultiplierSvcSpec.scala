package com.mooney.mathsvc.service

import com.mooney.mathsvc.BaseActorSpec
import com.mooney.mathsvc.service.MultiplierSvc.{MultiplicationInputs, MultiplicationResult}
import org.scalatest.prop.TableDrivenPropertyChecks._

class MultiplierSvcSpec extends BaseActorSpec("multiplierSvc") {

  trait Fixture {
    val multiplerSvcActor = system.actorOf(MultiplierSvc.props)
  }

  "The Multiplier Service" must "respond with the multiplication result" in new Fixture {
    multiplerSvcActor ! MultiplicationInputs(3, 4)
    expectMsg(MultiplicationResult(12))
  }

  it must "properly calculate th expected result" in {
    val tableData = Table(
      ("multiplicand", "multiplier", "result"),
      (2L, 4L, 8L),
      (0L, 3L, 0L),
      (3L, 0L, 0L),
      (1L, 1L, 1L),
      (5L, -1L, -5L),
      (-2L, 7L, -14L))

    forAll(tableData) { (multiplicand: Long, multiplier: Long, result: Long) =>
      MultiplierSvc.multiply(multiplicand, multiplier) shouldBe result
    }
  }
}
