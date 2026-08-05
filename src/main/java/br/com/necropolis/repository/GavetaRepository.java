package br.com.necropolis.repository;

import br.com.necropolis.entity.Gaveta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GavetaRepository extends JpaRepository<Gaveta, Long> {

    boolean existsByLoteIdAndNumero(
            Long loteId,
            Integer numero
    );

    long countByLoteId(Long loteId);
}