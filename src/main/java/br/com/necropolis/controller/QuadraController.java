package br.com.necropolis.controller;

import br.com.necropolis.dto.request.QuadraRequest;
import br.com.necropolis.dto.response.QuadraResponse;
import br.com.necropolis.service.QuadraService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quadras")
public class QuadraController {

    private final QuadraService service;

    public QuadraController(QuadraService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<QuadraResponse> cadastrar(
            @Valid @RequestBody QuadraRequest request
    ) {
        QuadraResponse response = service.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}