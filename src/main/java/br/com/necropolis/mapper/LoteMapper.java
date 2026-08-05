package br.com.necropolis.mapper;

import org.springframework.stereotype.Component;

import br.com.necropolis.dto.response.LoteResponse;
import br.com.necropolis.entity.Lote;

@Component
public class LoteMapper {
    public LoteResponse toResponse(Lote lote) {

        return new LoteResponse(
                lote.getId(),
                lote.getNumero(),
                lote.getDescricao(),
                lote.getCapacidade(),
                lote.getStatus(),
                lote.getAtivo(),
                lote.getQuadra().getId(),
                lote.getQuadra().getNome(),
                lote.getCreatedAt(),
                lote.getUpdatedAt());
    }
    
}
