package org.quickstart.springboot.prometheus;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final MeterRegistry meterRegistry;
    private Counter bidderCallsCounter;

    /**
     * We inject the MeterRegistry into this class
     */
    public GreetingController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        // CREATE A COUNTER
        bidderCallsCounter = this.meterRegistry.counter("bidder.calls", "type", "bidder");
    }

    /**
     * The @Timed annotation adds timing support, so we can see how long
     * it takes to execute in Prometheus
     * percentiles
     */
    @GetMapping("/greeting")
    @Timed(value = "greeting.time", description = "Time taken to return greeting", percentiles = {0.5, 0.90})
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        // THE INCREMENTER
        bidderCallsCounter.increment();
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

}
