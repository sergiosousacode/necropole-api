package br.com.necropolis.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record FalecidoRequest(

        @NotBlank(message = "O nome do falecido é obrigatório")
        @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres")
        String nome,

        LocalDate dataNascimento,

        @NotNull(message = "A data do óbito é obrigatória")
        LocalDate dataObito,

        @Size(max = 10, message = "O CID-10 deve ter no máximo 10 caracteres")
        String causaBasicaCid10,

        @Size(max = 500, message = "A observação deve ter no máximo 500 caracteres")
        String observacao,

        Boolean ativo
) {
}