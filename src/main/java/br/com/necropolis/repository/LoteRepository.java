package br.com.necropolis.repository;

import br.com.necropolis.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<Lote, Long> {

    boolean existsByQuadraIdAndNumeroIgnoreCase(
            Long quadraId,
            String numero
    );
}