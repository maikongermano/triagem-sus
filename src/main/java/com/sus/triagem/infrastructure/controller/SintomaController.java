package com.sus.triagem.infrastructure.controller;

import com.sus.triagem.application.service.SintomaService;
import com.sus.triagem.domain.model.Sintoma;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sintomas")
@RequiredArgsConstructor
public class SintomaController {

    private final SintomaService sintomaService;

    @PostMapping
    public ResponseEntity<Sintoma> salvar(@RequestBody Sintoma sintoma) {
        return ResponseEntity.ok(sintomaService.salvar(sintoma));
    }

    @GetMapping
    public ResponseEntity<List<Sintoma>> listarTodos() {
        return ResponseEntity.ok(sintomaService.listarTodos());
    }
}
