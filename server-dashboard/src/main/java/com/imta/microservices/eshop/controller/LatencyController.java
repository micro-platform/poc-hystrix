package com.imta.microservices.eshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

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


    @RequestMapping(value = "/latency",
            method = RequestMethod.GET)
    public Integer latency() {
        final int min = 0;
        final int max = 2000;

        final int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        try {
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return randomNum;
    }
}
