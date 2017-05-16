package com.imta.microservices.eshop; /**
 * Created by ssinigag on 04/05/17.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>This class is the starting point of the Spring Boot Application.</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@SpringBootApplication
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }

}



