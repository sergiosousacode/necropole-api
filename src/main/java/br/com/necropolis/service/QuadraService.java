package br.com.necropolis.service;


import br.com.necropolis.dto.request.QuadraRequest;
import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.entity.Quadra;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.QuadraMapper;
import br.com.necropolis.repository.CemiterioRepository;
import br.com.necropolis.repository.QuadraRepository;
import org.springframework.stereotype.Service;

@Service
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

    public QuadraResponse cadastrar(QuadraRequest request) {

    Cemiterio cemiterio = cemiterioRepository.findById(request.cemiterioId())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Cemitério não encontrado com o ID: " + request.cemiterioId()
                    )
            );

    Quadra quadra = new Quadra();
    quadra.setNome(request.nome());
    quadra.setDescricao(request.descricao());
    quadra.setCemiterio(cemiterio);

    if (request.ativo() != null) {
        quadra.setAtivo(request.ativo());
    }

    Quadra salva = quadraRepository.save(quadra);

    return mapper.toResponse(salva);
    }
}