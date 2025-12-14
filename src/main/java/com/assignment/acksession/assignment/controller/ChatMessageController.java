package com.assignment.acksession.assignment.controller;

import com.assignment.acksession.assignment.model.MessageRequest;
import com.assignment.acksession.assignment.model.MessageResponse;
import com.assignment.acksession.assignment.servcie.ChatMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/message")
@AllArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest message) {
        return new ResponseEntity<>(chatMessageService.sendMessage(message), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MessageResponse>> getMessages(@RequestParam String userId, @RequestParam String sessionId) {
        return new ResponseEntity<>(chatMessageService.getMessages(userId, sessionId), HttpStatus.OK);
    }
}
