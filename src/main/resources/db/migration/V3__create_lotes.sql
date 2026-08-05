CREATE TABLE lotes (
    id BIGSERIAL PRIMARY KEY,
    numero VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    capacidade INTEGER NOT NULL DEFAULT 3,
    status VARCHAR(30) NOT NULL DEFAULT 'LIVRE',
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    quadra_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_lotes_quadras
        FOREIGN KEY (quadra_id)
        REFERENCES quadras(id),

    CONSTRAINT uk_lotes_numero_quadra
        UNIQUE (quadra_id, numero),

    CONSTRAINT ck_lotes_capacidade_positiva
        CHECK (capacidade > 0)
);