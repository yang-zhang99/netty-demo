package com.y.netty;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/webflux")
@RestController
public class GreetingController {

    @GetMapping("/mono")
    public Mono<Greeting> mono() {
        return Mono.just(new Greeting("hello Greeting"));
    }

    @GetMapping("/flux")
    public Flux<Greeting> flux() {
        Flux<Greeting> f = Flux.just(new Greeting("hello greeting"));
        return f;
    }

}