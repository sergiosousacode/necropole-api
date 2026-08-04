package br.com.necropolis.service;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.dto.response.CemiterioResponse;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.mapper.CemiterioMapper;
import br.com.necropolis.repository.CemiterioRepository;
import br.com.necropolis.exception.ResourceNotFoundException;
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
            .orElseThrow(() ->
        new ResourceNotFoundException(
                "Cemitério não encontrado com o ID: " + id
        )
);

        return mapper.toResponse(cemiterio);
    }

    public CemiterioResponse atualizar(Long id, CemiterioRequest request) {
    Cemiterio cemiterio = repository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Cemitério não encontrado com o ID: " + id
                    )
            );

    cemiterio.setNome(request.nome());
    cemiterio.setEndereco(request.endereco());

    if (request.ativo() != null) {
        cemiterio.setAtivo(request.ativo());
    }

    Cemiterio atualizado = repository.save(cemiterio);

    return mapper.toResponse(atualizado);
    }

    public void excluir(Long id) {
    Cemiterio cemiterio = repository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Cemitério não encontrado com o ID: " + id
                    )
            );

    repository.delete(cemiterio);
}
}