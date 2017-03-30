package com.mooney.mathsvc.service

import java.util.concurrent.TimeUnit

import com.codahale.metrics.ConsoleReporter
import nl.grons.metrics.scala.DefaultInstrumented

trait SvcMetrics extends DefaultInstrumented {

  ConsoleReporter.forRegistry(metricRegistry).build().start(10, TimeUnit.SECONDS)
}
