package br.com.necropolis.repository;

import br.com.necropolis.entity.Falecido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FalecidoRepository implements PanacheRepository<Falecido> {

    public boolean existsByNomeIgnoreCase(String nome) {

        return count(
                "lower(nome) = lower(?1)",
                nome
        ) > 0;
    }
}