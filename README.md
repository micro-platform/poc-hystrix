# Poc-hystrix

This project experiments on Netflix's [Hystrix](https://github.com/Netflix/Hystrix) implementation
of the circuit-breaker pattern. 

## Installation

### Prerequisites

* [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/) - Dependency Management

Clone this project :

```git clone https://github.com/micro-platform/poc-hystrix.git ```

## Description

This project is composed of 3 components.

* ```hystrix-dashboard``` : standalone jar from [kennedyoliveira repository](https://github.com/kennedyoliveira/standalone-hystrix-dashboard).
* ```server-mock-latency``` : this a spring-boot application which receive somes arithmeticals operations (+,-,/,*) and take in params a ```latency``` parameters in order to simulate a timeout like a long process from databases for example.
* ```client-service``` : this a spring-boot application consumed by a client (you) to ask arithmeticals operations. The ```service``` will transfert the query to ```server-mock-latency``` which make the calcul and return the result after a specified ```time latency```.

# TODO ADD DIAGRAMS.

## Deployment

### Starting the server-mock-latency

```
cd server-mock-latency
mvn spring-boot:run
```

A server will start at ```localhost:12020```

### Starting the service-client

```
cd service-client
mvn spring-boot:run
```

A webservice will start at ```localhost:11010```

### Enable hystrix-dashboard

```
cd hystrix-dashboard
java -jar standalone-hystrix-dashboard-{VERSION}-all.jar
```

The web interface is accessible on : ```http://localhost:7979/hystrix-dashboard/```

## Tests

### Connect Hystrix dashboard to hystrix-metrics from service-client

Go on http://localhost:7979/hystrix-dashboard/ and enter the following Stream : ```http://localhost:11010/hystrix.stream```, click on "Add Stream" then "Monitor Stream".

### Send a first request

Hystrix-dashboard will keep loading until a request is sent on the ```client-service```.
You can use tools like [Postman](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop) to send your http request, or just use ```curl``` as follow :

``` curl http://localhost:11010/add/5/4?latency=250 ```

We will send a request to the client-service in order to calcul ```5+4=?``` with a latency of ```250ms```. The client-service will send the request to server-mock-latency. This last makes the operation, wait for ```250ms``` and returns the result.

You can follow the trace of your request on Hystrix-dashboard.

### Simulate a timeout

The four operations have a timeout configurate to 500ms.
If the request is longer than 500ms, the ```fallback``` method of hystrix is called. In our case, we will except :
* Timeout for ```add``` and ```remove``` will result : ```-1``` as result.
* Timeout for ```multiply``` and ```divide``` will result : ```-2000``` as result.

So, let's try to reach a timeout (looking at the hystrix dashboard):

``` curl http://localhost:11010/add/5/4?latency=1000 ``` --> you will receive ```-1```.

``` curl http://localhost:11010/multiply/5/4?latency=800 ``` -->
you will receive ```-2000```.

### Stress the webservice and look hystrix play it circuit breaker role

The goal is to send many requests at the same time in a short period in order to let hystrix open the circuit.

You can use a tool like [wrk](https://github.com/wg/wrk) to stress the application.

```wrk -t12 -c400 -d30s http://127.0.0.1:11010/add/5/4?latency=100```

This runs a benchmark for 30 seconds, using 12 threads, and keeping 400 HTTP connections open.

You can observe on the hystrix dashboard the circuit state switch many times between "closed" and "open".
