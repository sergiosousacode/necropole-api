package br.com.necropolis.controller;

import br.com.necropolis.dto.request.QuadraRequest;
import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.service.QuadraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quadras")
public class QuadraController {

    private final QuadraService service;

    public QuadraController(QuadraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<QuadraResponse> cadastrar(
            @Valid @RequestBody QuadraRequest request) {
        QuadraResponse response = service.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<QuadraResponse>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuadraResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuadraResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody QuadraRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }

}