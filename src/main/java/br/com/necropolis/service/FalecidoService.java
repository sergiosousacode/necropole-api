package br.com.necropolis.service;

import br.com.necropolis.dto.request.FalecidoRequest;
import br.com.necropolis.dto.response.FalecidoResponse;
import br.com.necropolis.entity.Falecido;
import br.com.necropolis.exception.BusinessException;
import br.com.necropolis.exception.ResourceNotFoundException;
import br.com.necropolis.mapper.FalecidoMapper;
import br.com.necropolis.repository.FalecidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FalecidoService {

    private final FalecidoRepository falecidoRepository;
    private final FalecidoMapper mapper;

    public FalecidoService(
            FalecidoRepository falecidoRepository,
            FalecidoMapper mapper
    ) {
        this.falecidoRepository = falecidoRepository;
        this.mapper = mapper;
    }

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

        Falecido salvo = falecidoRepository.save(falecido);

        return mapper.toResponse(salvo);
    }

    public List<FalecidoResponse> listar() {
        return falecidoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    public FalecidoResponse buscarPorId(Long id) {
        return mapper.toResponse(buscarFalecido(id));
    }

    public FalecidoResponse atualizar(Long id, FalecidoRequest request) {

        Falecido falecido = buscarFalecido(id);

        falecido.setNome(request.nome());
        falecido.setDataNascimento(request.dataNascimento());
        falecido.setDataObito(request.dataObito());
        falecido.setCausaBasicaCid10(request.causaBasicaCid10());
        falecido.setObservacao(request.observacao());

        if (request.ativo() != null) {
            falecido.setAtivo(request.ativo());
        }

        Falecido atualizado = falecidoRepository.save(falecido);

        return mapper.toResponse(atualizado);
    }

    public void excluir(Long id) {
        Falecido falecido = buscarFalecido(id);
        falecidoRepository.delete(falecido);
    }

    private Falecido buscarFalecido(Long id) {
        return falecidoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Falecido não encontrado com o ID: " + id
                        )
                );
    }
}