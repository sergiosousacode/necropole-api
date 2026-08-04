package br.com.necropolis.dto.response;

import java.time.LocalDateTime;

public record QuadraResponse(
        Long id,
        String nome,
        String descricao,
        Boolean ativo,
        Long cemiterioId,
        String cemiterioNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}