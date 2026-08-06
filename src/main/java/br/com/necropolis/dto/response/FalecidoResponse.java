package br.com.necropolis.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FalecidoResponse(

        Long id,
        String nome,
        LocalDate dataNascimento,
        LocalDate dataObito,
        String causaBasicaCid10,
        String observacao,
        Boolean ativo,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}