package br.com.necropolis.service;

import br.com.necropolis.dto.request.FalecidoRequest;
import br.com.necropolis.dto.response.FalecidoResponse;
import br.com.necropolis.entity.Falecido;
import br.com.necropolis.exception.BusinessException;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.FalecidoMapper;
import br.com.necropolis.repository.FalecidoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class FalecidoService {

    private final FalecidoRepository falecidoRepository;
    private final FalecidoMapper mapper;

    public FalecidoService(
            FalecidoRepository falecidoRepository,
            FalecidoMapper mapper) {

        this.falecidoRepository = falecidoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public FalecidoResponse cadastrar(FalecidoRequest request) {

        if (falecidoRepository.existsByNomeIgnoreCase(request.nome())) {
            throw new BusinessException(
                    "Já existe um falecido cadastrado com esse nome."
            );
        }

        Falecido falecido = new Falecido();

        falecido.setNome(request.nome());
        falecido.setDataNascimento(request.dataNascimento());
        falecido.setDataObito(request.dataObito());
        falecido.setCausaBasicaCid10(request.causaBasicaCid10());
        falecido.setObservacao(request.observacao());

        if (request.ativo() != null) {
            falecido.setAtivo(request.ativo());
        }

        falecidoRepository.persist(falecido);

        return mapper.toResponse(falecido);
    }

    public List<FalecidoResponse> listar() {

        return falecidoRepository.listAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public FalecidoResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarFalecido(id));
    }

    @Transactional
    public FalecidoResponse atualizar(
            Long id,
            FalecidoRequest request) {

        Falecido falecido = buscarFalecido(id);

        falecido.setNome(request.nome());
        falecido.setDataNascimento(request.dataNascimento());
        falecido.setDataObito(request.dataObito());
        falecido.setCausaBasicaCid10(request.causaBasicaCid10());
        falecido.setObservacao(request.observacao());

        if (request.ativo() != null) {
            falecido.setAtivo(request.ativo());
        }

        return mapper.toResponse(falecido);
    }

    @Transactional
    public void excluir(Long id) {

        Falecido falecido = buscarFalecido(id);

        falecidoRepository.delete(falecido);
    }

    private Falecido buscarFalecido(Long id) {

        return falecidoRepository.findByIdOptional(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Falecido não encontrado com o ID: " + id
                        )
                );
    }
}