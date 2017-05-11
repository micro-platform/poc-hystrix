package com.imta.microservices.eshop.service;

import com.imta.microservices.eshop.HystrixProperties;
import com.imta.microservices.eshop.command.CalculCommand;
import com.imta.microservices.eshop.wrapper.Operation;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>TODO</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@Service
public class CalculService {

    private final String LATENCY_URI = "http://localhost:12020/calcul/";

    @HystrixCommand(fallbackMethod = "defaultCalcul",
            commandProperties = {
                    @HystrixProperty(name = HystrixProperties.TIMEOUT,
                            value = "500")
            })
    public Double askCalcul(Operation op, Integer a, Integer b) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(LATENCY_URI + op + "/" + a + "/" + b);
        return restTemplate.getForObject(LATENCY_URI + op + "/" + a + "/" + b, Double.class); //just to simulates long process.
    }


    //Fallback @todo javadoc
    public Double defaultCalcul(Operation op, Integer a, Integer b) {
        return Double.valueOf(-1);
    }


    //Alternatively we can use a Hystrix Command classes :

    //todo javadoc
    public Double askCalculV2(Operation op, Integer a, Integer b) {
        return new CalculCommand(LATENCY_URI, op, a, b).execute();
    }
}

