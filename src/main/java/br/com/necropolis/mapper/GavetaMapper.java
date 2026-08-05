package br.com.necropolis.mapper;

import org.springframework.stereotype.Component;

import br.com.necropolis.dto.response.GavetaResponse;
import br.com.necropolis.entity.Gaveta;

@Component
public class GavetaMapper {
    public GavetaResponse toResponse(Gaveta gaveta) {

        return new GavetaResponse(
                gaveta.getId(),
                gaveta.getNumero(),
                gaveta.getStatus(),
                gaveta.getAtivo(),
                gaveta.getLote().getId(),
                gaveta.getLote().getNumero(),
                gaveta.getCreatedAt(),
                gaveta.getUpdatedAt());
    }
    
}
