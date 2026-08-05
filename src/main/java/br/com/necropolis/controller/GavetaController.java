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

import br.com.necropolis.dto.request.GavetaRequest;
import br.com.necropolis.dto.response.GavetaResponse;
import br.com.necropolis.service.GavetaService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/gavetas")
public class GavetaController {
    private final GavetaService service;

    public GavetaController(GavetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GavetaResponse> cadastrar(
            @Valid @RequestBody GavetaRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<GavetaResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GavetaResponse> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GavetaResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody GavetaRequest request) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }

    
    
}
