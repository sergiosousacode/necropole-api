package br.com.necropolis.repository;

import br.com.necropolis.entity.Cemiterio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CemiterioRepository extends JpaRepository<Cemiterio, Long> {
}