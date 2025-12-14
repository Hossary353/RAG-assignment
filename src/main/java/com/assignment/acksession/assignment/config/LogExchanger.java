package com.assignment.acksession.assignment.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class LogExchanger extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        CustomHttpServletRequestWrapper requestPayload = new CustomHttpServletRequestWrapper(request);
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(response);


        try {
            log.info("HTTP [method]: [{}] ----->  [endpoint]: {}    [payload]: {}",
                    requestPayload.getMethod(),
                    requestPayload.getRequestURI(), requestPayload);

            filterChain.doFilter(requestPayload, responseWrapper);
        } finally {
            response.getOutputStream().write(responseWrapper.getBody().getBytes(StandardCharsets.UTF_8));
            long duration = System.currentTimeMillis() - startTime;

            log.info("HTTP [method]: [{}] <----- [endpoint]: {} [status]: [{}] [duration]: [{} ms]  [response body]:{}  [headers]:[{}]",
                    requestPayload.getMethod(),
                    requestPayload.getRequestURI(),
                    responseWrapper.getStatus(),
                    duration,
                    responseWrapper.getBody(),
                    responseWrapper.getHeaderNames());
        }
    }
}
