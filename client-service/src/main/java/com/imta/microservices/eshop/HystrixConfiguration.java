package com.imta.microservices.eshop;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ssinigag on 11/05/17.
 */
@Configuration
public class HystrixConfiguration {

    @Bean
    public HystrixCommandAspect hystrixAspect() {
        return new HystrixCommandAspect();
    }

    @Bean
    public ServletRegistrationBean hystrixStreamServlet(){
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(),"/hystrix.stream");
    }
}
