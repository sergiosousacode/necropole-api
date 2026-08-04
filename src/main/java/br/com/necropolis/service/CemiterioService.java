package br.com.necropolis.service;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.repository.CemiterioRepository;
import org.springframework.stereotype.Service;

@Service
public class CemiterioService {

    private final CemiterioRepository repository;

    public CemiterioService(CemiterioRepository repository) {
        this.repository = repository;
    }

    public Cemiterio cadastrar(CemiterioRequest request) {
        Cemiterio cemiterio = new Cemiterio();

        cemiterio.setNome(request.nome());
        cemiterio.setEndereco(request.endereco());

        if (request.ativo() != null) {
            cemiterio.setAtivo(request.ativo());
        }

        return repository.save(cemiterio);
    }
}