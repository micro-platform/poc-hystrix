package com.imta.microservices.eshop.command;

import com.imta.microservices.eshop.wrapper.Operation;
import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ssinigag on 11/05/17.
 */
public class CalculCommand extends HystrixCommand<Double> {

    private final Operation operation;
    private final Integer a;
    private final Integer b;
    private final String URI;

    public CalculCommand(String URI, Operation op, Integer a, Integer b) {
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("Calcul"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter().
                                withExecutionTimeoutInMilliseconds(500))
        );
        this.operation = op;
        this.a = a;
        this.b = b;
        this.URI = URI;
    }

    @Override
    protected Double getFallback() {
        return Double.valueOf(-2000);
    }


    @Override
    protected Double run() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(URI + operation + "/" + a + "/" + b);
        return restTemplate.getForObject(URI + operation + "/" + a + "/" + b, Double.class); //just to simulates long process.
    }
}
