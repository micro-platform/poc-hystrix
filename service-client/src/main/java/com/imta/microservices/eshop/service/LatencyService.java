package com.imta.microservices.eshop.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>TODO</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@Service
public class LatencyService {

    private final String LATENCY_URI = "http://localhost:12020/latency";

    @HystrixCommand(fallbackMethod = "defaultLatency")
    public Integer latency(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(LATENCY_URI, Integer.class); //just to simulates long process.
    }

    public Integer defaultLatency(){
        return -1;
    }
}
