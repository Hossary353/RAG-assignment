package com.assignment.acksession.assignment.controller;

import com.assignment.acksession.assignment.entity.ChatSession;
import com.assignment.acksession.assignment.model.SessionRequest;
import com.assignment.acksession.assignment.servcie.ChatSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/session")
@AllArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    @PostMapping
    public ResponseEntity<ChatSession> createSession(@RequestParam String userId){
        return new ResponseEntity<>(chatSessionService.createSession(userId),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ChatSession>> listAvailableSessions(@RequestParam String userId){
        return new ResponseEntity<>(chatSessionService.retrieveSessions(userId),HttpStatus.OK);
    }

    @PutMapping("/favourite/{favourite}")
    public ResponseEntity<ChatSession> markFavourite(@RequestBody @Validated SessionRequest sessionRequest, @PathVariable boolean favourite){
        return new ResponseEntity<>(chatSessionService.markFavourite(sessionRequest,favourite), HttpStatus.OK);
    }

    @PutMapping("/rename/{name}")
    public ResponseEntity<ChatSession> markFavourite(@RequestBody @Validated SessionRequest sessionRequest, @PathVariable String name){
        return new ResponseEntity<>(chatSessionService.renameSession(sessionRequest,name), HttpStatus.OK);
    }


}
