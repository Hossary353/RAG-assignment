package com.assignment.acksession.assignment.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@Slf4j
public class LogExchanger extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        CustomHttpServletRequestWrapper requestPayload = new CustomHttpServletRequestWrapper(request);
        try {
            log.info("HTTP [method]: [{}] ----->  [endpoint]: {}    [body]: [{}]",
                    requestPayload.getMethod(),
                    requestPayload.getRequestURI(), requestPayload);

            filterChain.doFilter(requestPayload, response);
        } finally {

            long duration = System.currentTimeMillis() - startTime;

            log.info("HTTP [method]: [{}] <----- [endpoint]: {} [status]: [{}] [duration]: [{} ms]  [headers]:[{}]",
                    requestPayload.getMethod(),
                    requestPayload.getRequestURI(),
                    response.getStatus(),
                    duration,
                    response.getHeaderNames());
        }
    }
}
