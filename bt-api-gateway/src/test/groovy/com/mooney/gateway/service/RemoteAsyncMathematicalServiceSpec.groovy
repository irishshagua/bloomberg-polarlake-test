package com.mooney.gateway.service

import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RemoteAsyncMathematicalServiceSpec extends Specification {

    def "A null multiplicand will throw an exception" () {
        given: "a null multiplicand"
            def multiplicand = null

        and: "a valid multiplier"
            def multiplier = 7L

        and: "the math service"
            def svc = new RemoteAsyncMathematicalServiceImpl(Mock(RestTemplate), "", 0)

        when: "the multiplication service call is made"
            svc.multiply(multiplicand, multiplier)

        then: "an NPE is thrown with a descriptive message"
            def ex = thrown(NullPointerException)
            ex.message == "multiplicand"
    }

    def "A null multiplier will throw an exception" () {
        given: "a null multiplier"
            def multiplier = null

        and: "a valid multiplicand"
            def multiplicand = 7L

        and: "the math service"
            def svc = new RemoteAsyncMathematicalServiceImpl(Mock(RestTemplate), "", 0)

        when: "the multiplication service call is made"
            svc.multiply(multiplicand, multiplier)

        then: "an NPE is thrown with a descriptive message"
            def ex = thrown(NullPointerException)
            ex.message == "multiplier"
    }

    def "The correct route will be used for the service call" () {
        given: "a service with defined inputs"
            def mockedRestTemplate = Mock(RestTemplate)
            def svc = new RemoteAsyncMathematicalServiceImpl(mockedRestTemplate, "daHost", 54321)

        when: "the multiple method is called"
            svc.multiply(4, 8)

        then: "the correct url is used in the rest template call"
            1 * mockedRestTemplate.getForObject(_, _, _) >> "32"
    }
}
