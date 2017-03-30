package com.mooney.mathsvc.http

import akka.actor.ActorRef
import akka.http.scaladsl.server.Route

import scala.concurrent.ExecutionContext

class HttpApi(multiplySvc: ActorRef)(implicit executionContext: ExecutionContext) {

  val multiplicationRoute = new MultiplicationRoute(multiplySvc)

  val routes: Route = multiplicationRoute.route
}
