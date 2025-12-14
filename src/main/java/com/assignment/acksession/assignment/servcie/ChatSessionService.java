package com.assignment.acksession.assignment.servcie;

import com.assignment.acksession.assignment.converter.SessionMapper;
import com.assignment.acksession.assignment.entity.ChatSession;
import com.assignment.acksession.assignment.model.DeleteResponse;
import com.assignment.acksession.assignment.model.SessionRequest;
import com.assignment.acksession.assignment.model.SessionResponse;
import com.assignment.acksession.assignment.repository.ChatSessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ChatSessionService {

    private final ChatSessionRepository chatSessionRepository;
    private final SessionMapper mapper;


    public SessionResponse createSession(String userId) {

        ChatSession chatSession = ChatSession.builder()
                .userId(userId)
                .createdAt(LocalDateTime.now())
                .title("new Session")
                .build();
        var saved = chatSessionRepository.save(chatSession);
        return mapper.transform(saved);
    }

    @Transactional(readOnly = true)
    public List<SessionResponse> retrieveSessions(String userId) {
        var sessionEntity = chatSessionRepository.findAllByUserIdOrderByUpdatedAtDesc(userId);
        return sessionEntity.stream().map(mapper::transform).toList();
    }

    public SessionResponse markFavourite(SessionRequest sessionRequest, boolean favourite) {

        var chatSession = chatSessionRepository.findByIdAndUserId(UUID.fromString(sessionRequest.getSessionId()), sessionRequest.getUserId());
        if (chatSession == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        } else {
            chatSession.setFavourite(favourite);
            chatSession.setUpdatedAt(LocalDateTime.now());
            var saved = chatSessionRepository.save(chatSession);
            return mapper.transform(saved);
        }


    }

    public SessionResponse renameSession(SessionRequest sessionRequest, String name) {
        var chatSession = chatSessionRepository.findByIdAndUserId(UUID.fromString(sessionRequest.getSessionId()), sessionRequest.getUserId());
        if (chatSession == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        } else {
            chatSession.setTitle(name);
            chatSession.setUpdatedAt(LocalDateTime.now());
            var saved = chatSessionRepository.save(chatSession);
            return mapper.transform(saved);
        }

    }

    public DeleteResponse deleteSession(SessionRequest sessionRequest) {
        var chatSession = chatSessionRepository.findByIdAndUserId(UUID.fromString(sessionRequest.getSessionId()), sessionRequest.getUserId());
        if (chatSession == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        } else {

            chatSessionRepository.delete(chatSession);
            return new DeleteResponse("Record Deleted");
        }

    }

    public ChatSession getSessionById(String userId, String sessionId) {

        var session = chatSessionRepository.findByIdAndUserId(UUID.fromString(sessionId), userId);
        if (session == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return session;
    }
}
