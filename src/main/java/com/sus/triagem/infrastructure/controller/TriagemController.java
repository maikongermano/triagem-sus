package com.sus.triagem.infrastructure.controller;

import com.sus.triagem.application.service.TriagemService;
import com.sus.triagem.domain.model.Triagem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/triagens")
@RequiredArgsConstructor
public class TriagemController {

    private final TriagemService triagemService;

    @PostMapping
    public ResponseEntity<Triagem> realizarTriagem(@RequestParam Long pacienteId, @RequestBody List<Long> sintomasIds) {
        return ResponseEntity.ok(triagemService.realizarTriagem(pacienteId, sintomasIds));
    }

    @GetMapping
    public ResponseEntity<List<Triagem>> listarPorPrioridade() {
        return ResponseEntity.ok(triagemService.listarPorPrioridade());
    }
}
