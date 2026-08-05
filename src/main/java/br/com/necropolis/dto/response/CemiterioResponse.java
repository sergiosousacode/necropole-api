package br.com.necropolis.dto.response;

import java.time.LocalDateTime;

public record CemiterioResponse(
                Long id,
                String nome,
                String endereco,
                Boolean ativo,
                LocalDateTime createdAt,
                LocalDateTime updatedAt) {
}