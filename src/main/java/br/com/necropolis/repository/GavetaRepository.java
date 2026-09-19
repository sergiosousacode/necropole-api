package br.com.necropolis.repository;

import br.com.necropolis.entity.Gaveta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GavetaRepository implements PanacheRepository<Gaveta> {

    public boolean existsByLoteIdAndNumero(
            Long loteId,
            Integer numero) {

        return count(
                "lote.id = ?1 and numero = ?2",
                loteId,
                numero
        ) > 0;
    }

    public long countByLoteId(Long loteId) {

        return count(
                "lote.id = ?1",
                loteId
        );
    }
}