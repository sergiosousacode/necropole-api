package br.com.necropolis.service;

import br.com.necropolis.dto.request.QuadraRequest;
import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.entity.Quadra;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.QuadraMapper;
import br.com.necropolis.repository.CemiterioRepository;
import br.com.necropolis.repository.QuadraRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class QuadraService {

    private final QuadraRepository quadraRepository;
    private final CemiterioRepository cemiterioRepository;
    private final QuadraMapper mapper;

    public QuadraService(
            QuadraRepository quadraRepository,
            CemiterioRepository cemiterioRepository,
            QuadraMapper mapper) {

        this.quadraRepository = quadraRepository;
        this.cemiterioRepository = cemiterioRepository;
        this.mapper = mapper;
    }

    @Transactional
    public QuadraResponse cadastrar(QuadraRequest request) {

        Cemiterio cemiterio = cemiterioRepository
                .findByIdOptional(request.cemiterioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cemitério não encontrado com o ID: "
                                + request.cemiterioId()));

        Quadra quadra = new Quadra();

        quadra.setNome(request.nome());
        quadra.setDescricao(request.descricao());
        quadra.setCemiterio(cemiterio);

        if (request.ativo() != null) {
            quadra.setAtivo(request.ativo());
        }

        quadraRepository.persist(quadra);

        return mapper.toResponse(quadra);
    }

    public List<QuadraResponse> listar() {

        return quadraRepository.listAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public QuadraResponse buscarPorId(Long id) {

        Quadra quadra = quadraRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Quadra não encontrada com o ID: " + id));

        return mapper.toResponse(quadra);
    }

    @Transactional
    public QuadraResponse atualizar(Long id, QuadraRequest request) {

        Quadra quadra = quadraRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Quadra não encontrada com o ID: " + id));

        quadra.setNome(request.nome());
        quadra.setDescricao(request.descricao());

        if (request.ativo() != null) {
            quadra.setAtivo(request.ativo());
        }

        return mapper.toResponse(quadra);
    }

    @Transactional
    public void excluir(Long id) {

        Quadra quadra = quadraRepository.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Quadra não encontrada com o ID: " + id));

        quadraRepository.delete(quadra);
    }
}