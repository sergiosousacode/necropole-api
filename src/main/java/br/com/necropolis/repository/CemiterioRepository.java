package br.com.necropolis.repository;

import br.com.necropolis.entity.Cemiterio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CemiterioRepository implements PanacheRepository<Cemiterio> {

}