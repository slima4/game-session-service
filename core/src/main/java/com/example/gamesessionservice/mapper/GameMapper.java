package com.example.gamesessionservice.mapper;

import com.example.gameengineservice.dto.GameResponse;
import com.example.gameengineservice.model.Game;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {

    Game toGame(GameResponse gameResponse);
}
