package com.assignment.acksession.assignment.servcie;

import com.assignment.acksession.assignment.converter.MessageMapper;
import com.assignment.acksession.assignment.entity.ChatMessage;
import com.assignment.acksession.assignment.entity.Sender;
import com.assignment.acksession.assignment.model.MessageRequest;
import com.assignment.acksession.assignment.model.MessageResponse;
import com.assignment.acksession.assignment.repository.ChatMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final ChatSessionService chatSessionService;
    private final List<String> replies;

    @Transactional
    public MessageResponse sendMessage(MessageRequest message) {
        var session = chatSessionService.getSessionById(message.getUserId(), message.getSessionId());
        ChatMessage sentMessage = ChatMessage.builder()
                .chatSession(session)
                .content(message.getMessage())
                .sender(Sender.USER)
                .createdAt(LocalDateTime.now())
                .build();

        ChatMessage receivedResponse = ChatMessage.builder()
                .chatSession(session)
                .content(replies.get(new Random().nextInt(replies.size() - 1)))
                .sender(Sender.AI)
                .createdAt(LocalDateTime.now())
                .build();

        var list = List.of(sentMessage, receivedResponse);
        messageRepository.saveAll(list);
        return messageMapper.revert(receivedResponse);
    }

    @Transactional
    public List<MessageResponse> getMessages(String userId, String sessionId) {
        var session = chatSessionService.getSessionById(userId, sessionId);
        var messages = session.getChatMessages();
        return messages.stream().map(messageMapper::revert).toList();
    }
}
