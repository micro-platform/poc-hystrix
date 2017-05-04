package com.imta.microservices.eshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>TODO</p>
 *
 * @author Steeve Sinigaglia
 * @since 0.1
 */
@RestController
public class CalculController {

    @RequestMapping("/add")
    public Integer latency() {
        // TODO: 04/05/17 implements route
        return 66;
    }
}
