package com.mooney.mathsvc

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}

class BaseActorSpec(actorSystem: ActorSystem) extends TestKit(actorSystem) with ImplicitSender with BaseSpec {

  def this(actorSystemName: String) = this(ActorSystem(actorSystemName))
}
