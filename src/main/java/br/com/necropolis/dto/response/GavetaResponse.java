package br.com.necropolis.dto.response;

import br.com.necropolis.enums.StatusGaveta;

import java.time.LocalDateTime;

public record GavetaResponse(
        Long id,
        Integer numero,
        StatusGaveta status,
        Boolean ativo,
        Long loteId,
        String loteNumero,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}