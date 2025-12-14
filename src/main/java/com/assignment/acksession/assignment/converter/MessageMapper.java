package com.assignment.acksession.assignment.converter;

import com.assignment.acksession.assignment.entity.ChatMessage;
import com.assignment.acksession.assignment.model.MessageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    ChatMessage transform(MessageResponse response);

    MessageResponse revert(ChatMessage message);

}
