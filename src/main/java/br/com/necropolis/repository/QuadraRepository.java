package br.com.necropolis.repository;

import br.com.necropolis.entity.Quadra;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuadraRepository implements PanacheRepository<Quadra> {

    public boolean existsByCemiterioIdAndNomeIgnoreCase(
            Long cemiterioId,
            String nome) {

        return count(
                "cemiterio.id = ?1 and lower(nome) = lower(?2)",
                cemiterioId,
                nome
        ) > 0;
    }
}