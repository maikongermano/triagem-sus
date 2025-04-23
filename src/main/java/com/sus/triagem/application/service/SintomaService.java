package com.sus.triagem.application.service;

import com.sus.triagem.domain.model.Sintoma;

import java.util.List;

public interface SintomaService {
    Sintoma salvar(Sintoma sintoma);
    List<Sintoma> listarTodos();
}
