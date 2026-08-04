package br.com.necropolis.controller;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.dto.response.CemiterioResponse;
import br.com.necropolis.service.CemiterioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/cemiterios")
public class CemiterioController {

    private final CemiterioService service;

    public CemiterioController(CemiterioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CemiterioResponse> cadastrar(
            @Valid @RequestBody CemiterioRequest request
    ) {
        CemiterioResponse response = service.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<CemiterioResponse>> listar() {
    
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CemiterioResponse> buscarPorId(
            @PathVariable Long id
    ) {
    
    return ResponseEntity.ok(service.buscarPorId(id));
}

}