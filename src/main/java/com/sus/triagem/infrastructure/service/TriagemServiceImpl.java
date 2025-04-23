package com.sus.triagem.infrastructure.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sus.triagem.application.service.TriagemService;
import com.sus.triagem.domain.model.Paciente;
import com.sus.triagem.domain.model.Sintoma;
import com.sus.triagem.domain.model.Triagem;
import com.sus.triagem.domain.repository.PacienteRepository;
import com.sus.triagem.domain.repository.SintomaRepository;
import com.sus.triagem.domain.repository.TriagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TriagemServiceImpl implements TriagemService {

    private final TriagemRepository triagemRepository;

    private final PacienteRepository pacienteRepository;

    private final SintomaRepository sintomaRepository;

    @Override
    public Triagem realizarTriagem(Long pacienteId, List<Long> sintomasIds) {

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        List<Sintoma> sintomas = sintomaRepository.findAllById(sintomasIds);

        String prioridade = definirPrioridade(sintomas);

        Triagem triagem = Triagem.builder()
                .paciente(paciente)
                .sintomas(sintomas)
                .prioridade(prioridade)
                .dataHora(LocalDateTime.now())
                .build();

        return triagemRepository.save(triagem);
    }

    @Override
    public List<Triagem> listarPorPrioridade() {

        return triagemRepository.findAllByOrderByPrioridadeAscDataHoraAsc();
    }

    private String definirPrioridade(List<Sintoma> sintomas) {

        for (Sintoma s : sintomas) {
            String desc = s.getDescricao().toLowerCase();
            if (desc.contains("dor no peito") || desc.contains("falta de ar") || desc.contains("inconsciente")) {
                return "EMERGENCIA";
            }
        }

        if (sintomas.size() >= 3) {
            return "URGENTE";
        }

        return "NAO_URGENTE";
    }
}
