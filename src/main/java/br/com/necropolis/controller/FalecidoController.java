package br.com.necropolis.controller;

import br.com.necropolis.dto.request.FalecidoRequest;
import br.com.necropolis.dto.response.FalecidoResponse;
import br.com.necropolis.service.FalecidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/falecidos")
public class FalecidoController {

    private final FalecidoService service;

    public FalecidoController(FalecidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FalecidoResponse> cadastrar(
            @Valid @RequestBody FalecidoRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<FalecidoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FalecidoResponse> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FalecidoResponse> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody FalecidoRequest request
    ) {
        return ResponseEntity.ok(service.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}