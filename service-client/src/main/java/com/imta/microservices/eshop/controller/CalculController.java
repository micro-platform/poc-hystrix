package com.imta.microservices.eshop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Provide 4 routes for basic calculation.</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@RestController(value = "/calcul")
public class CalculController {

    private final String LATENCY_URI = "http://localhost:12020/latency";

    /**
     * Calcul the sum between a and b.
     *
     * @param a first number (integer).
     * @param b second number (integer).
     * @return the sum of a+b.
     */
    @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
    public Integer add(@PathVariable Integer a,
                       @PathVariable Integer b) {
        final Integer result = a + b;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(LATENCY_URI, Integer.class); //just to simulates long process.
        return result;
    }

    /**
     * Calcul the substraction between a and b.
     *
     * @param a first number (integer).
     * @param b second number (integer).
     * @return the sum of a-b.
     */
    @RequestMapping(value = "/substract/{a}/{b}", method = RequestMethod.GET)
    public Integer remove(@PathVariable Integer a,
                          @PathVariable Integer b) {
        final Integer result = a - b;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(LATENCY_URI, Integer.class); //just to simulates long process.
        return result;
    }

    /**
     * Calcul the multiplication between a and b.
     *
     * @param a first number (integer).
     * @param b second number (integer).
     * @return the sum of a*b.
     */
    @RequestMapping(value = "/multiply/{a}/{b}", method = RequestMethod.GET)
    public Double multiply(@PathVariable Integer a,
                           @PathVariable Integer b) {
        final Double result = Double.valueOf(a * b);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(LATENCY_URI, Integer.class); //just to simulates long process.
        return result;
    }

    /**
     * Calcul the divide between a and b.
     *
     * @param a first number (integer).
     * @param b second number (integer).
     * @return the sum of a/b.
     */
    @RequestMapping(value = "/divide/{a}/{b}", method = RequestMethod.GET)
    public Double divide(@PathVariable Integer a,
                         @PathVariable Integer b) {
        final Double result = Double.valueOf(a / b);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(LATENCY_URI, Integer.class); //just to simulates long process.
        return result;
    }
}
