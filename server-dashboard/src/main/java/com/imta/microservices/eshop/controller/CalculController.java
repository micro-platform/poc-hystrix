package com.imta.microservices.eshop.controller;

import com.imta.microservices.eshop.wrapper.Operation;
import org.springframework.web.bind.annotation.PathVariable;
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
public class CalculController {


    @RequestMapping(value = "/calcul/{op}/{a}/{b}",
            method = RequestMethod.GET)
    public Double calcul(@PathVariable Operation op,
                         @PathVariable Integer a,
                         @PathVariable Integer b) {
        final int min = 0;
        final int max = 2000;

        Double result;

        switch (op) {
            case ADD:
                result = Double.valueOf(a + b);
                break;
            case SUBSTRACT:
                result = Double.valueOf(a - b);
                break;
            case MULTIPLY:
                result = Double.valueOf(a * b);
                break;
            case DIVIDE:
                result = Double.valueOf(a / b);
                break;
            default:
                throw new RuntimeException("Error operator don't exist");
        }


        final int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        System.out.println("receive " + a + op + b + "=" + result);
        System.out.println("wait for " + randomNum);
        try {
            Thread.sleep(randomNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
