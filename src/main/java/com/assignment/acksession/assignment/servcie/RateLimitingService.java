package com.assignment.acksession.assignment.servcie;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimitingService {

    private final ConcurrentMap<String, Bucket> cache = new ConcurrentHashMap<>();

    private static final Bandwidth LIMIT = Bandwidth.builder()
            .capacity(3)
            .refillIntervally(5, Duration.ofMinutes(1))
            .build();


    public Bucket resolveBucket(String key) {
        return cache.computeIfAbsent(key, this::createNewBucket);
    }

    private Bucket createNewBucket(String key) {
        return Bucket.builder().addLimit(LIMIT).build();
    }
}
