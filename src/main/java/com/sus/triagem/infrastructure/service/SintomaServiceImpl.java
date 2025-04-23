package com.sus.triagem.infrastructure.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sus.triagem.application.service.SintomaService;
import com.sus.triagem.domain.model.Sintoma;
import com.sus.triagem.domain.repository.SintomaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SintomaServiceImpl implements SintomaService {
    private final SintomaRepository sintomaRepository;

    @Override
    public Sintoma salvar(Sintoma sintoma) {
        return sintomaRepository.save(sintoma);
    }

    @Override
    public List<Sintoma> listarTodos() {
        return sintomaRepository.findAll();
    }
}