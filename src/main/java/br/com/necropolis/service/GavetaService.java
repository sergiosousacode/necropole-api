package br.com.necropolis.service;

import br.com.necropolis.dto.request.GavetaRequest;
import br.com.necropolis.dto.response.GavetaResponse;
import br.com.necropolis.entity.Gaveta;
import br.com.necropolis.entity.Lote;
import br.com.necropolis.exception.BusinessException;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.GavetaMapper;
import br.com.necropolis.repository.GavetaRepository;
import br.com.necropolis.repository.LoteRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class GavetaService {

    private final GavetaRepository gavetaRepository;
    private final LoteRepository loteRepository;
    private final GavetaMapper mapper;

    public GavetaService(
            GavetaRepository gavetaRepository,
            LoteRepository loteRepository,
            GavetaMapper mapper) {

        this.gavetaRepository = gavetaRepository;
        this.loteRepository = loteRepository;
        this.mapper = mapper;
    }

    public GavetaResponse cadastrar(GavetaRequest request) {

        Lote lote = loteRepository.findById(request.loteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Lote não encontrado com o ID: " + request.loteId()));

        Gaveta gaveta = new Gaveta();

        gaveta.setNumero(request.numero());
        gaveta.setLote(lote);

        if (request.status() != null) {
            gaveta.setStatus(request.status());
        }

        if (request.ativo() != null) {
            gaveta.setAtivo(request.ativo());
        }

        if (gavetaRepository.existsByLoteIdAndNumero(
                request.loteId(),
                request.numero())) {

            throw new BusinessException(
                    "Já existe uma gaveta com esse número neste lote.");
        }

        long quantidade = gavetaRepository.countByLoteId(lote.getId());

        if (quantidade >= lote.getCapacidade()) {

            throw new BusinessException(
                    "O lote já atingiu sua capacidade máxima.");
        }

        Gaveta salva = gavetaRepository.save(gaveta);

        return mapper.toResponse(salva);
    }

    public List<GavetaResponse> listar() {
        return gavetaRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public GavetaResponse buscarPorId(Long id) {

        Gaveta gaveta = gavetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gaveta não encontrada com o ID: " + id));
        return mapper.toResponse(gaveta);
    }

    public GavetaResponse atualizar(Long id, GavetaRequest request) {

        Gaveta gaveta = gavetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Gaveta não encontrada com o ID: " + id));

        Lote lote = loteRepository.findById(request.loteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Lote não encontrado com o ID: " + request.loteId()));

        gaveta.setNumero(request.numero());
        gaveta.setLote(lote);

        if (request.status() != null) {
            gaveta.setStatus(request.status());
        }

        if (request.ativo() != null) {
            gaveta.setAtivo(request.ativo());
        }

        Gaveta atualizada = gavetaRepository.save(gaveta);

        return mapper.toResponse(atualizada);
    }

    public void excluir(Long id) {
        Gaveta gaveta = gavetaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Gaveta não encontrada com o ID " + id));

        gavetaRepository.delete(gaveta);
    }

}