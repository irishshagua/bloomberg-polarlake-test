package com.mooney.gateway.config;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Predicates;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableSwagger2
@EnableMetrics
public class ApiGatewayConfig extends MetricsConfigurerAdapter {

  @Value("${rest.client.read.timeout}")
  private Integer restReadTimeout;
  @Value("${rest.client.conn.timeout}")
  private Integer restConnectionTimeout;

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate(clientHttpRequestFactory());
  }

  private ClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
    factory.setReadTimeout(restReadTimeout);
    factory.setConnectTimeout(restConnectionTimeout);
    return factory;
  }

  @Override
  public void configureReporters(MetricRegistry registry) {
    registerReporter(ConsoleReporter.forRegistry(registry).build()).start(10, TimeUnit.SECONDS);
  }

  @Bean
  public Docket apiDocumentation() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(Predicates.or(
            PathSelectors.regex("/operations.*"),
            PathSelectors.regex("/health")))
        .build();
  }
}
