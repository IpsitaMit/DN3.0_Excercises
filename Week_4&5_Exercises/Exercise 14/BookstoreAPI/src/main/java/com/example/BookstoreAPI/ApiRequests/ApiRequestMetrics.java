package com.example.BookstoreAPI.ApiRequests;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ApiRequestMetrics {

    private final Counter requestCounter;

    public ApiRequestMetrics(MeterRegistry meterRegistry) {
        this.requestCounter = meterRegistry.counter("api.requests.total");
    }

    public void incrementRequestCount() {
        this.requestCounter.increment();
    }
}
