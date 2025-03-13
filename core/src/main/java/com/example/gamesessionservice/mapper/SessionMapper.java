package com.example.gamesessionservice.mapper;

import com.example.gamesessionservice.dto.CreateSessionResponse;
import com.example.gamesessionservice.dto.SessionResponse;
import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper
public interface SessionMapper {

    CreateSessionResponse toCreateSessionResponse(UUID id);

    SessionResponse toSessionResponse(UUID id);
}
