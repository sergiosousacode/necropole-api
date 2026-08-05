package br.com.necropolis.dto.request;

import br.com.necropolis.enums.StatusGaveta;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GavetaRequest(
    @NotNull(message = "O número de gavetas é obrigatório")
    @Positive(message = "O número da gaveta deve ser maior que zero")
    Integer numero,


    StatusGaveta status,
    Boolean ativo,

    @NotNull(message = "O lote é obrigatório")
    Long loteId
) {
    
}
