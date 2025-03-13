package com.example.gamesessionservice.controller;

import com.example.gamesessionservice.dto.CreateSessionResponse;
import com.example.gamesessionservice.dto.SessionResponse;
import com.example.gamesessionservice.mapper.SessionMapper;
import com.example.gamesessionservice.service.SessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Tag(name = "Session")
@RestController
@RequestMapping(value = "api/v1/session", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SessionController {

    SessionService sessionService;
    SessionMapper sessionMapper;

    @PostMapping
    public CreateSessionResponse createSession() {
        final UUID sessionId = sessionService.createSession();
        return sessionMapper.toCreateSessionResponse(sessionId);
    }

    @PostMapping("/{sessionId}/simulate")
    public ResponseEntity<Void> simulateGame(@PathVariable UUID sessionId) {
        sessionService.simulateGame(sessionId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sessionId}")
    public SessionResponse getSession(@PathVariable UUID sessionId) {
        final UUID id = sessionService.getSession(sessionId);
        return sessionMapper.toSessionResponse(id);
    }
}
