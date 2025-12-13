package com.assignment.acksession.assignment.repository;

import com.assignment.acksession.assignment.entity.ChatSession;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ChatSessionRepository extends CrudRepository<ChatSession, UUID> {
    List<ChatSession> findAllByUserIdOrderByUpdatedAtDesc(String userId);
    List<ChatSession> findByUserId(String userId);
    ChatSession findByIdAndUserId(UUID id,String userId);
}
