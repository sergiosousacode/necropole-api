package br.com.necropolis.dto.request;

import br.com.necropolis.enums.StatusLote;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LoteRequest(

        @NotBlank(message = "O número do lote é obrigatório")
        @Size(max = 50, message = "O número deve ter no máximo 50 caracteres")
        String numero,

        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao,

        @NotNull(message = "A capacidade é obrigatória")
        @Positive(message = "A capacidade deve ser maior que zero")
        Integer capacidade,

        StatusLote status,

        Boolean ativo,

        @NotNull(message = "A quadra é obrigatória")
        Long quadraId
) {
}