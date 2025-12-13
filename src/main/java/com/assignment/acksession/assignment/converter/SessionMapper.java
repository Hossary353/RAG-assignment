package com.assignment.acksession.assignment.converter;

import com.assignment.acksession.assignment.entity.ChatSession;
import com.assignment.acksession.assignment.model.SessionResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionResponse transform(ChatSession session);

    ChatSession revert(SessionResponse response);
}
