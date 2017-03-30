package com.mooney.mathsvc.service

import akka.actor.{Actor, Props}
import com.mooney.mathsvc.service.MultiplierSvc.{MultiplicationInputs, MultiplicationResult}
import nl.grons.metrics.scala._
import nl.grons.metrics.scala.DefaultInstrumented
import com.mooney.mathsvc.service.MultiplierSvc._

object MultiplierSvc {

  case class MultiplicationInputs(multiplicand: Long, multiplier: Long)
  case class MultiplicationResult(result: Long)

  def props = Props(new MultiplierSvcInstrumented)

  def multiply: (Long, Long) => Long = (multiplicand, multiplier) => multiplicand * multiplier
}

class MultiplierSvc extends Actor with DefaultInstrumented {

  override def receive: Receive = {
    case MultiplicationInputs(multiplicand, multiplier) =>
      sender() ! MultiplicationResult(multiply(multiplicand, multiplier))
  }
}

class MultiplierSvcInstrumented extends MultiplierSvc
  with ActorInstrumentedLifeCycle with ReceiveTimerActor