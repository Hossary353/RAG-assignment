package com.assignment.acksession.assignment.model;

import lombok.Data;

@Data
public class MessageRequest {

    private String sessionId;

    private String userId;

    private String message;

}
