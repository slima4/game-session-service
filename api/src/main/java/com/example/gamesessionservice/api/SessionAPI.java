package com.example.gamesessionservice.api;

import com.example.gamesessionservice.dto.CreateSessionResponse;
import com.example.gamesessionservice.dto.SessionResponse;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
    value = "session-integration-service",
    url = "${integration.game-session.host:0.0.0.0}",
    path = "api/v1/session"
)
public interface SessionAPI {

    @PostMapping
    CreateSessionResponse createSession();

    @PostMapping("/{sessionId}/simulate")
    void simulateGame(@PathVariable UUID sessionId);

    @GetMapping("/{sessionId}")
    SessionResponse getSession(@PathVariable UUID sessionId);
}
