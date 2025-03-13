package com.example.gamesessionservice.mapper;

import com.example.gamesessionservice.entity.SessionEntity;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface SessionEntityMapper {

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "moveHistory", ignore = true)
    SessionEntity toSessionEntity(UUID id);
}
