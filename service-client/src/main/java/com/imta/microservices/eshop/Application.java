package com.imta.microservices.eshop; /**
 * Created by ssinigag on 04/05/17.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * <p>This class is the starting point of the Spring Boot Application.</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@SpringBootApplication
@EnableCircuitBreaker
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}



