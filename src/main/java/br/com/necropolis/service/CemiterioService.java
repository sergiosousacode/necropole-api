package br.com.necropolis.service;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.dto.response.CemiterioResponse;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.mapper.CemiterioMapper;
import br.com.necropolis.repository.CemiterioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CemiterioService {

    private final CemiterioRepository repository;
    private final CemiterioMapper mapper;

    public CemiterioService(
        CemiterioRepository repository,
        CemiterioMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public CemiterioResponse cadastrar(CemiterioRequest request) {
        Cemiterio cemiterio = mapper.toEntity(request);
        Cemiterio salvo = repository.save(cemiterio);

        return mapper.toResponse(salvo);
    }

    public List<CemiterioResponse> listar() {
        
        return repository.findAll()
            .stream()
            .map(mapper::toResponse)
            .toList();
    }

    public CemiterioResponse buscarPorId(Long id){
        Cemiterio cemiterio = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Cemitério não encontrado"));

        return mapper.toResponse(cemiterio);
    }
}