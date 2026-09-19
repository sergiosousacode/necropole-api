package br.com.necropolis.repository;

import br.com.necropolis.entity.Lote;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoteRepository implements PanacheRepository<Lote> {

    public boolean existsByQuadraIdAndNumeroIgnoreCase(
            Long quadraId,
            String numero) {

        return count(
                "quadra.id = ?1 and lower(numero) = lower(?2)",
                quadraId,
                numero
        ) > 0;
    }
}