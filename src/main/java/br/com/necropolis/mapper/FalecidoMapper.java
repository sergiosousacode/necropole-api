package br.com.necropolis.mapper;

import br.com.necropolis.dto.response.FalecidoResponse;
import br.com.necropolis.entity.Falecido;
import org.springframework.stereotype.Component;

@Component
public class FalecidoMapper {

    public FalecidoResponse toResponse(Falecido falecido) {

        return new FalecidoResponse(
                falecido.getId(),
                falecido.getNome(),
                falecido.getDataNascimento(),
                falecido.getDataObito(),
                falecido.getCausaBasicaCid10(),
                falecido.getObservacao(),
                falecido.getAtivo(),
                falecido.getCreatedAt(),
                falecido.getUpdatedAt()
        );
    }
}