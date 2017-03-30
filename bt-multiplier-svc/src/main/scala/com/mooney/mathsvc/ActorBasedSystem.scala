package com.mooney.mathsvc

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.ActorMaterializer
import com.mooney.mathsvc.service.MultiplierSvc

import scala.concurrent.ExecutionContext

trait ActorBasedSystem {

  implicit val actorSystem: ActorSystem = ActorSystem()
  implicit val execCtxt: ExecutionContext = actorSystem.dispatcher
  implicit val materializer: ActorMaterializer = ActorMaterializer()
}
