package br.com.necropolis.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuadraRequest(

                @NotBlank(message = "O nome da quadra é obrigatório") @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres") String nome,

                @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres") String descricao,

                Boolean ativo,

                @NotNull(message = "O cemitério é obrigatório") Long cemiterioId) {
}