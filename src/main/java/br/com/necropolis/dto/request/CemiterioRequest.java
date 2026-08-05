package br.com.necropolis.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CemiterioRequest(

                @NotBlank(message = "O nome é obrigatório") @Size(max = 150, message = "O nome deve ter no máximo 150 caracteres") String nome,

                @Size(max = 255, message = "O endereço deve ter no máximo 255 caracteres") String endereco,

                Boolean ativo) {
}