package com.mooney.mathsvc.http

import akka.actor.ActorRef
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.pattern.ask
import akka.util.Timeout
import com.mooney.mathsvc.service.MultiplierSvc._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class MultiplicationRoute(multiplicationActor: ActorRef)(implicit executionContext: ExecutionContext) {

  implicit val timeout: Timeout = 400 milliseconds

  val route: Route = path("multiply" / LongNumber / LongNumber) { (multiplicand, multiplier) =>
    get {
      val multiplicationResult = (multiplicationActor ? MultiplicationInputs(multiplicand, multiplier)).mapTo[MultiplicationResult]
      onComplete(multiplicationResult) {
        case Success(result) => complete(s"${result.result}")
        case Failure(ex) => failWith(ex)
      }
    }
  }
}
