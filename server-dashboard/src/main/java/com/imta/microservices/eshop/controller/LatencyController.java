package com.imta.microservices.eshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>This is the RestController which give a route
 * called by an another services (hystrix client) in order
 * to simulate a latency on the response and measure it with
 * hystrix. </p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@RestController
public class LatencyController {

    @RequestMapping("/latency")
    public Integer latency() {
        // TODO: 04/05/17 implements latency
        return 66;
    }
}
