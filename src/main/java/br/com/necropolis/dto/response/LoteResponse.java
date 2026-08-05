package br.com.necropolis.dto.response;

import br.com.necropolis.enums.StatusLote;

import java.time.LocalDateTime;

public record LoteResponse(
        Long id,
        String numero,
        String descricao,
        Integer capacidade,
        StatusLote status,
        Boolean ativo,
        Long quadraId,
        String quadraNome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}