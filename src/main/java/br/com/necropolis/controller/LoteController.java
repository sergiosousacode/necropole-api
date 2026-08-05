package br.com.necropolis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.necropolis.dto.request.LoteRequest;
import br.com.necropolis.dto.request.QuadraRequest;
import br.com.necropolis.dto.response.LoteResponse;
import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.service.LoteService;
import br.com.necropolis.service.QuadraService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {
    private final LoteService service;

    public LoteController(LoteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoteResponse> cadastrar(
            @Valid @RequestBody LoteRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<LoteResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LoteRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
    
}
