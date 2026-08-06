package br.com.necropolis.repository;

import br.com.necropolis.entity.Falecido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FalecidoRepository extends JpaRepository<Falecido, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}