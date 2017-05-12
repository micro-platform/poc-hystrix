package com.imta.microservices.eshop.controller;

import com.imta.microservices.eshop.service.CalculService;
import com.imta.microservices.eshop.wrapper.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Provide 4 routes for basic calculation.</p>
 * <p>The + and - operations call the service which use @HystrixCommand annotation.</p>
 * <p>The * and / operations call the service which use external Hystrix Command.</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@RestController(value = "/calcul")
public class CalculController {

    @Autowired
    CalculService calculService;

    /**
     * Calcul the sum between a and b.
     *
     * @param a first number (integer).
     * @param b second number (integer).
     * @return the sum of a+b.
     */
    @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
    public Double add(@PathVariable Integer a,
                      @PathVariable Integer b,
                      @RequestParam Integer latency) {
        return calculService.askCalcul(Operation.ADD, a, b, latency);
    }

    /**
     * Calcul the substraction between a and b.
     *
     * @param a first number (integer).
     * @param b second number (integer).
     * @return the sum of a-b.
     */
    @RequestMapping(value = "/substract/{a}/{b}", method = RequestMethod.GET)
    public Double substract(@PathVariable Integer a,
                            @PathVariable Integer b,
                            @RequestParam Integer latency) {
        return calculService.askCalcul(Operation.SUBSTRACT, a, b, latency);
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
                           @PathVariable Integer b,
                           @RequestParam Integer latency) {
        return calculService.askCalculV2(Operation.MULTIPLY, a, b, latency);
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
                         @PathVariable Integer b,
                         @RequestParam Integer latency) {
        return calculService.askCalculV2(Operation.DIVIDE, a, b, latency);
    }
}
