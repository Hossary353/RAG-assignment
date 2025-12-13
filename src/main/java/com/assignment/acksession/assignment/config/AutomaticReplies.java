package com.assignment.acksession.assignment.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class AutomaticReplies {

    @Bean
    public List<String> replies() {

        List<String> messages = new ArrayList<>();
        try {

            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("AI-response/reply.json");
            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(is);


            for (JsonNode node : root) {
                messages.add(node.get("message").asText());
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return messages;
    }

}
