package com.mooney.mathsvc

import com.typesafe.config.ConfigFactory

trait MultiplierSvcConfig {

  private val config = ConfigFactory.load()
  private val httpConfig = config.getConfig("http")

  val httpInterface: String = httpConfig.getString("interface")
  val httpPort: Int = httpConfig.getInt("port")
}
