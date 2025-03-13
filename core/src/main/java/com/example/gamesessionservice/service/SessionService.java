package com.example.gamesessionservice.service;

import com.example.gameengineservice.api.GameAPI;
import com.example.gamesessionservice.entity.SessionEntity;
import com.example.gamesessionservice.mapper.GameMapper;
import com.example.gamesessionservice.mapper.MoveMapper;
import com.example.gameengineservice.model.Game;
import com.example.gameengineservice.model.Move;
import com.example.gamesessionservice.mapper.SessionEntityMapper;
import com.example.gamesessionservice.repository.SessionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SessionService {

    GameAPI gameAPI;
    SessionRepository sessionRepository;
    GameMapper gameMapper;
    MoveMapper moveMapper;
    SessionEntityMapper sessionMapper;

    public UUID createSession() {
        final UUID id = gameMapper.toGame(gameAPI.createGame()).getId();
        final SessionEntity sessionEntity = sessionMapper.toSessionEntity(id);
        sessionRepository.save(sessionEntity);
        return id;
    }

    public void simulateGame(UUID sessionId) {
        int moves = 0;
        while (moves < 9) {
            Game game = gameMapper.toGame(gameAPI.getGameState(sessionId));
            if (game == null)
                throw new RuntimeException("Game not found");

            if (!"IN_PROGRESS".equals(game.getStatus()))
                break;

            int moveCount = game.getMoveHistory().size();
            char player = (moveCount % 2 == 0) ? 'X' : 'O';

            List<int[]> availableCells = getAvailableCells(game.getBoard());
            if (availableCells.isEmpty())
                break;

            int[] randomCell = availableCells.get(new Random().nextInt(availableCells.size()));
            Move move = new Move(randomCell[0], randomCell[1], player);
            String status = gameAPI.makeMove(sessionId, moveMapper.toMoveRequest(move)).getStatus();
            if (!"IN_PROGRESS".equals(status))
                break;

            moves++;
        }
    }

    public UUID getSession(UUID sessionId) {
        return sessionRepository.findSessionEntityById(sessionId).orElseThrow(
            () -> new RuntimeException("Session not found")
        ).getId();
    }

    private List<int[]> getAvailableCells(char[][] board) {
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    cells.add(new int[]{i, j});
                }
            }
        }
        return cells;
    }
}
