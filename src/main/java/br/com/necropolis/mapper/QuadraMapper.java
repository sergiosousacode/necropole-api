package br.com.necropolis.mapper;

import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.entity.Quadra;
import org.springframework.stereotype.Component;

@Component
public class QuadraMapper {
    public QuadraResponse toResponse(Quadra quadra) {

        return new QuadraResponse(
                quadra.getId(),
                quadra.getNome(),
                quadra.getDescricao(),
                quadra.getAtivo(),
                quadra.getCemiterio().getId(),
                quadra.getCemiterio().getNome(),
                quadra.getCreatedAt(),
                quadra.getUpdatedAt());
    }
}