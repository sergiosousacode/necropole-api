package br.com.necropolis.controller;

import br.com.necropolis.dto.request.CemiterioRequest;
import br.com.necropolis.entity.Cemiterio;
import br.com.necropolis.service.CemiterioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cemiterios")
public class CemiterioController {

    private final CemiterioService service;

    public CemiterioController(CemiterioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cemiterio> cadastrar(
            @Valid @RequestBody CemiterioRequest request) {

        Cemiterio cemiterio = service.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cemiterio);
    }
}