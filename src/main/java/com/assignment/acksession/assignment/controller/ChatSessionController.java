package com.assignment.acksession.assignment.controller;

import com.assignment.acksession.assignment.model.DeleteResponse;
import com.assignment.acksession.assignment.model.SessionRequest;
import com.assignment.acksession.assignment.model.SessionResponse;
import com.assignment.acksession.assignment.servcie.ChatSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/session")
@AllArgsConstructor
public class ChatSessionController {

    private final ChatSessionService chatSessionService;

    @PostMapping
    public ResponseEntity<SessionResponse> createSession(@RequestParam String userId) {
        return new ResponseEntity<>(chatSessionService.createSession(userId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SessionResponse>> listAvailableSessions(@RequestParam String userId) {
        return new ResponseEntity<>(chatSessionService.retrieveSessions(userId), HttpStatus.OK);
    }

    @PutMapping("/favourite/{favourite}")
    public ResponseEntity<SessionResponse> markFavourite(@RequestBody SessionRequest sessionRequest, @PathVariable boolean favourite) {
        return new ResponseEntity<>(chatSessionService.markFavourite(sessionRequest, favourite), HttpStatus.OK);
    }

    @PutMapping("/rename/{name}")
    public ResponseEntity<SessionResponse> rename(@RequestBody SessionRequest sessionRequest, @PathVariable String name) {
        return new ResponseEntity<>(chatSessionService.renameSession(sessionRequest, name), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<DeleteResponse> deleteSession(@RequestBody SessionRequest sessionRequest) {

        return new ResponseEntity<>(chatSessionService.deleteSession(sessionRequest), HttpStatus.NO_CONTENT);
    }


}
