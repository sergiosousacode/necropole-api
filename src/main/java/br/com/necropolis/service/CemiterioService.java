package br.com.necropolis.service;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.dto.response.CemiterioResponse;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.CemiterioMapper;
import br.com.necropolis.repository.CemiterioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CemiterioService {

    private final CemiterioRepository repository;
    private final CemiterioMapper mapper;

    public CemiterioService(
            CemiterioRepository repository,
            CemiterioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CemiterioResponse cadastrar(CemiterioRequest request) {

        Cemiterio cemiterio = mapper.toEntity(request);

        repository.persist(cemiterio);

        return mapper.toResponse(cemiterio);
    }

    public List<CemiterioResponse> listar() {

        return repository.listAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public CemiterioResponse buscarPorId(Long id) {

        Cemiterio cemiterio = repository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cemitério não encontrado com o ID: " + id));

        return mapper.toResponse(cemiterio);
    }

    @Transactional
    public CemiterioResponse atualizar(Long id, CemiterioRequest request) {

        Cemiterio cemiterio = repository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cemitério não encontrado com o ID: " + id));

        cemiterio.setNome(request.nome());
        cemiterio.setEndereco(request.endereco());

        if (request.ativo() != null) {
            cemiterio.setAtivo(request.ativo());
        }

        return mapper.toResponse(cemiterio);
    }

    @Transactional
    public void excluir(Long id) {

        Cemiterio cemiterio = repository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cemitério não encontrado com o ID: " + id));

        repository.delete(cemiterio);
    }
}