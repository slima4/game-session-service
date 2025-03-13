CREATE TABLE IF NOT EXISTS session_entity
(
    id         UUID PRIMARY KEY,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS move_history
(
    id         UUID PRIMARY KEY,
    session_id UUID                        NOT NULL,
    row        INT                         NOT NULL,
    col        INT                         NOT NULL,
    symbol     CHAR(1)                     NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),

    CONSTRAINT fk_session FOREIGN KEY (session_id) REFERENCES session_entity (id) ON DELETE CASCADE
);
