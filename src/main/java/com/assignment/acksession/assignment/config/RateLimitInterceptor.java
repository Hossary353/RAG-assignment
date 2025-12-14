package com.assignment.acksession.assignment.config;

import com.assignment.acksession.assignment.servcie.RateLimitingService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class RateLimitInterceptor implements HandlerInterceptor {

    private final RateLimitingService rateLimitingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientKey = request.getRemoteAddr();

        Bucket bucket = rateLimitingService.resolveBucket(clientKey);

        if (bucket.tryConsume(1)) {
            return true;
        } else {
            response.setStatus(429);
            response.getWriter().write("You have exceeded your rate limit.");

            long availableInSeconds = bucket.getAvailableTokens() / 1_000_000_000;
            response.setHeader("Retry-After", String.valueOf(availableInSeconds));

            return false;
        }
    }

}
