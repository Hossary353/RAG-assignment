package com.assignment.acksession.assignment.model;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponse {

    private String id;

    private String userId;

    private String title;

    private boolean favourite;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
