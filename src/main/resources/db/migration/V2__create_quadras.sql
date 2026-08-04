CREATE TABLE quadras (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    cemiterio_id BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,

    CONSTRAINT fk_quadras_cemiterios
        FOREIGN KEY (cemiterio_id)
        REFERENCES cemiterios(id),

    CONSTRAINT uk_quadras_nome_cemiterio
        UNIQUE (cemiterio_id, nome)
);