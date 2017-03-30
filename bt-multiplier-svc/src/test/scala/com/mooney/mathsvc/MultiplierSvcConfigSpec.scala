package com.mooney.mathsvc

class MultiplierSvcConfigSpec extends BaseSpec {

  "Config Trait" must "resolve the correct values for the http server" in new MultiplierSvcConfig {
    httpInterface shouldBe "0.0.0.0"
    httpPort shouldBe 8090
  }
}
