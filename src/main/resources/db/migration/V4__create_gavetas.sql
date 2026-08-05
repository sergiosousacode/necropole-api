CREATE TABLE gavetas (
    id BIGSERIAL PRIMARY KEY,
    numero INTEGER NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'LIVRE',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    lote_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_gavetas_lotes
        FOREIGN KEY (lote_id)
        REFERENCES lotes(id),

    CONSTRAINT uk_gavetas_numero_lote
        UNIQUE (lote_id, numero),

    CONSTRAINT ck_gavetas_numero_positivo
        CHECK (numero > 0)
);