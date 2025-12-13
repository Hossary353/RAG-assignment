package com.assignment.acksession.assignment.servcie;

import com.assignment.acksession.assignment.entity.ChatSession;
import com.assignment.acksession.assignment.model.SessionRequest;
import com.assignment.acksession.assignment.repository.ChatSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;


    public ChatSession createSession(String userId) {

        ChatSession chatSession = ChatSession.builder()
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .title("new Session")
                .build();

        return chatSessionRepository.save(chatSession);
    }


    public List<ChatSession> retrieveSessions(String userId) {

        return chatSessionRepository.findAllByUserIdOrderByUpdatedAtDesc(userId);
    }

    public ChatSession markFavourite(SessionRequest sessionRequest, boolean favourite) {

        var chatSession = chatSessionRepository.findByIdAndUserId(UUID.fromString(sessionRequest.getSessionId()),sessionRequest.getUserId());
        if (chatSession ==null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }else {
            chatSession.setFavorite(favourite);
            chatSession.setUpdatedAt(LocalDateTime.now());
            return chatSessionRepository.save(chatSession);
        }


    }

    public ChatSession renameSession(SessionRequest sessionRequest, String name) {
        var chatSession = chatSessionRepository.findByIdAndUserId(UUID.fromString(sessionRequest.getSessionId()),sessionRequest.getUserId());
        if (chatSession ==null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }else {
            chatSession.setTitle(name);
            chatSession.setUpdatedAt(LocalDateTime.now());
            return chatSessionRepository.save(chatSession);
        }

    }
}
