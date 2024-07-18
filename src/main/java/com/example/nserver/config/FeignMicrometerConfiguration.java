package com.example.nserver.config;

import feign.Capability;
import org.springframework.context.annotation.Bean;
import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignMicrometerConfiguration {

    @Bean
    public Capability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
}