package com.example.gamesessionservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity(name = "move_history")
@Table(name = "move_history")
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoveHistoryEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "UUID")
    UUID id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    SessionEntity session;

    @Column(name = "row", nullable = false)
    int row;

    @Column(name = "col", nullable = false)
    int col;

    @Column(name = "symbol", nullable = false)
    char symbol;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    Instant updatedAt;
}
