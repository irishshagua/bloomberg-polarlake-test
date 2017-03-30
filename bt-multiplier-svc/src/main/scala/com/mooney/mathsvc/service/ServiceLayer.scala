package com.mooney.mathsvc.service

import akka.actor.ActorRef
import com.mooney.mathsvc.ActorBasedSystem

trait ServiceLayer extends SvcMetrics {
  this: ActorBasedSystem =>

  val multiplierSvc: ActorRef = actorSystem.actorOf(MultiplierSvc.props)
}
