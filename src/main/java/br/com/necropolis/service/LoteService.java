package br.com.necropolis.service;

import br.com.necropolis.dto.request.LoteRequest;
import br.com.necropolis.dto.response.LoteResponse;
import br.com.necropolis.entity.Lote;
import br.com.necropolis.entity.Quadra;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.LoteMapper;
import br.com.necropolis.repository.LoteRepository;
import br.com.necropolis.repository.QuadraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteService {

    private final LoteRepository loteRepository;
    private final QuadraRepository quadraRepository;
    private final LoteMapper mapper;

    public LoteService(
            LoteRepository loteRepository,
            QuadraRepository quadraRepository,
            LoteMapper mapper
    ) {
        this.loteRepository = loteRepository;
        this.quadraRepository = quadraRepository;
        this.mapper = mapper;
    }

    public LoteResponse cadastrar(LoteRequest request) {
        Quadra quadra = buscarQuadra(request.quadraId());

        Lote lote = new Lote();
        lote.setNumero(request.numero());
        lote.setDescricao(request.descricao());
        lote.setCapacidade(request.capacidade());
        lote.setQuadra(quadra);

        if (request.status() != null) {
            lote.setStatus(request.status());
        }

        if (request.ativo() != null) {
            lote.setAtivo(request.ativo());
        }

        Lote salvo = loteRepository.save(lote);

        return mapper.toResponse(salvo);
    }

    public List<LoteResponse> listar() {
        return loteRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public LoteResponse buscarPorId(Long id) {
        Lote lote = buscarLote(id);

        return mapper.toResponse(lote);
    }

    public LoteResponse atualizar(Long id, LoteRequest request) {
        Lote lote = buscarLote(id);
        Quadra quadra = buscarQuadra(request.quadraId());

        lote.setNumero(request.numero());
        lote.setDescricao(request.descricao());
        lote.setCapacidade(request.capacidade());
        lote.setQuadra(quadra);

        if (request.status() != null) {
            lote.setStatus(request.status());
        }

        if (request.ativo() != null) {
            lote.setAtivo(request.ativo());
        }

        Lote atualizado = loteRepository.save(lote);

        return mapper.toResponse(atualizado);
    }

    public void excluir(Long id) {
        Lote lote = buscarLote(id);
        loteRepository.delete(lote);
    }

    private Lote buscarLote(Long id) {
        return loteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Lote não encontrado com o ID: " + id
                        )
                );
    }

    private Quadra buscarQuadra(Long id) {
        return quadraRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Quadra não encontrada com o ID: " + id
                        )
                );
    }
}