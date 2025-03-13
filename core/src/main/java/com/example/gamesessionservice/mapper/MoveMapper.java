package com.example.gamesessionservice.mapper;

import com.example.gameengineservice.dto.MoveRequest;
import com.example.gameengineservice.model.Move;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface MoveMapper {

    @Mapping(target = "symbol", expression = "java(String.valueOf(move.getSymbol()))")
    MoveRequest toMoveRequest(Move move);
}
