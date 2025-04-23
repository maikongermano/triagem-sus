package com.sus.triagem.application.service;

import com.sus.triagem.domain.model.Paciente;

import java.util.List;

public interface PacienteService {
    Paciente salvar(Paciente paciente);
    List<Paciente> listarTodos();
    Paciente buscarPorId(Long id);
    void deletar(Long id);
}
