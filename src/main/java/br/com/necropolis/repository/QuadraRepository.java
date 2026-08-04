package br.com.necropolis.repository;

import br.com.necropolis.entity.Quadra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuadraRepository extends JpaRepository<Quadra, Long> {

    boolean existsByCemiterioIdAndNomeIgnoreCase(
            Long cemiterioId,
            String nome
    );
}