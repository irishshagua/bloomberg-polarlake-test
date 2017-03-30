package com.mooney.mathsvc

import akka.http.scaladsl.Http
import com.mooney.mathsvc.http.HttpApi
import com.mooney.mathsvc.service.ServiceLayer

object MathematicalOperationsSvc extends App
  with ActorBasedSystem with ServiceLayer with MultiplierSvcConfig {

  val httpSvc = new HttpApi(multiplierSvc)

  Http().bindAndHandle(httpSvc.routes, httpInterface, httpPort)
}
