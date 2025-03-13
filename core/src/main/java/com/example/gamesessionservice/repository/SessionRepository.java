package com.example.gamesessionservice.repository;

import com.example.gamesessionservice.entity.SessionEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, UUID> {

    @EntityGraph(attributePaths = "moveHistory")
    Optional<SessionEntity> findSessionEntityById(UUID id);
}
