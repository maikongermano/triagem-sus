package com.sus.triagem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sus.triagem.domain.model.Paciente;
import com.sus.triagem.domain.model.Sintoma;
import com.sus.triagem.domain.model.Triagem;
import com.sus.triagem.domain.repository.PacienteRepository;
import com.sus.triagem.domain.repository.SintomaRepository;
import com.sus.triagem.domain.repository.TriagemRepository;
import com.sus.triagem.infrastructure.service.TriagemServiceImpl;

class TriagemServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @Mock
    private SintomaRepository sintomaRepository;

    @Mock
    private TriagemRepository triagemRepository;

    @InjectMocks
    private TriagemServiceImpl triagemService;

    private Paciente paciente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paciente = Paciente.builder()
                .id(1L)
                .nome("José")
                .cpf("12345678900")
                .build();
    }

    @Test
    void deveClassificarComoEmergencia() {
        Sintoma sintomaGrave = Sintoma.builder().descricao("Dor no peito").build();
        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(sintomaRepository.findAllById(List.of(1L))).thenReturn(List.of(sintomaGrave));
        when(triagemRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Triagem triagem = triagemService.realizarTriagem(1L, List.of(1L));

        assertEquals("EMERGENCIA", triagem.getPrioridade());
    }

    @Test
    void deveClassificarComoUrgenteComTresSintomas() {
        List<Sintoma> sintomas = List.of(
                Sintoma.builder().descricao("Febre").build(),
                Sintoma.builder().descricao("Dor de cabeça").build(),
                Sintoma.builder().descricao("Tosse").build()
        );

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(sintomaRepository.findAllById(List.of(1L, 2L, 3L))).thenReturn(sintomas);
        when(triagemRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Triagem triagem = triagemService.realizarTriagem(1L, List.of(1L, 2L, 3L));

        assertEquals("URGENTE", triagem.getPrioridade());
    }

    @Test
    void deveClassificarComoNaoUrgente() {
        List<Sintoma> sintomas = List.of(
                Sintoma.builder().descricao("Dor no pé").build()
        );

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));
        when(sintomaRepository.findAllById(List.of(1L))).thenReturn(sintomas);
        when(triagemRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Triagem triagem = triagemService.realizarTriagem(1L, List.of(1L));

        assertEquals("NAO_URGENTE", triagem.getPrioridade());
    }

    @Test
    void deveLancarErroSePacienteNaoExiste() {
        when(pacienteRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            triagemService.realizarTriagem(999L, List.of());
        });

        assertTrue(excecao.getMessage().contains("Paciente não encontrado"));
    }
}
