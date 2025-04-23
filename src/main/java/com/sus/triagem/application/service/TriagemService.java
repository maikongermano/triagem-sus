package com.sus.triagem.application.service;

import com.sus.triagem.domain.model.Triagem;

import java.util.List;

public interface TriagemService {
    Triagem realizarTriagem(Long pacienteId, List<Long> sintomasIds);
    List<Triagem> listarPorPrioridade();
}
