package com.imta.microservices.eshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ssinigag on 04/05/17.
 */
@RestController
public class LatencyController {

    @RequestMapping("/latency")
    public Integer latency() {
        // TODO: 04/05/17 implements latency
        return 66;
    }
}
