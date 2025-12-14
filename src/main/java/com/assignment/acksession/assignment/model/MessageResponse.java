package com.assignment.acksession.assignment.model;

import com.assignment.acksession.assignment.entity.Sender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {

    private String id;

    private String sessionId;

    private Sender sender;

    private String content;

    private String context;

    private LocalDateTime createdAt = LocalDateTime.now();

}
