package br.com.necropolis.mapper;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.dto.response.CemiterioResponse;
import br.com.necropolis.entity.Cemiterio;
import org.springframework.stereotype.Component;

@Component
public class CemiterioMapper {

    public Cemiterio toEntity(CemiterioRequest request) {
        Cemiterio cemiterio = new Cemiterio();

        cemiterio.setNome(request.nome());
        cemiterio.setEndereco(request.endereco());

        if (request.ativo() != null) {
            cemiterio.setAtivo(request.ativo());
        }

        return cemiterio;
    }

    public CemiterioResponse toResponse(Cemiterio cemiterio) {
        return new CemiterioResponse(
                cemiterio.getId(),
                cemiterio.getNome(),
                cemiterio.getEndereco(),
                cemiterio.getAtivo(),
                cemiterio.getCreatedAt(),
                cemiterio.getUpdatedAt()

        );
    }
}