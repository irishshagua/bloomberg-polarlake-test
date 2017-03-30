package com.mooney.mathsvc

import akka.actor.ActorSystem
import akka.http.scaladsl.testkit.ScalatestRouteTest

class BaseHttpSpec(actorSystem: ActorSystem) extends BaseSpec with ScalatestRouteTest {

  def this(actorSystemName: String) = this(ActorSystem(actorSystemName))
}
