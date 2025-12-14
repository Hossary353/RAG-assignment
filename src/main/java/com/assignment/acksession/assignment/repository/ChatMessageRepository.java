package com.assignment.acksession.assignment.repository;

import com.assignment.acksession.assignment.entity.ChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, UUID> {

}
