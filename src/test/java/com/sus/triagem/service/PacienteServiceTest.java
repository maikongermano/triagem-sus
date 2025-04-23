package com.sus.triagem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sus.triagem.domain.model.Paciente;
import com.sus.triagem.domain.repository.PacienteRepository;
import com.sus.triagem.infrastructure.service.PacienteServiceImpl;

@ExtendWith(MockitoExtension.class)
class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Test
    void deveSalvarPaciente() {
        Paciente paciente = Paciente.builder().nome("Maria").cpf("12345678900").build();

        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        Paciente salvo = pacienteService.salvar(paciente);

        assertEquals("Maria", salvo.getNome());
        verify(pacienteRepository).save(paciente);
    }

    @Test
    void deveListarTodosPacientes() {
        List<Paciente> lista = List.of(
                Paciente.builder().nome("João").build(),
                Paciente.builder().nome("Ana").build()
        );

        when(pacienteRepository.findAll()).thenReturn(lista);

        List<Paciente> resultado = pacienteService.listarTodos();

        assertEquals(2, resultado.size());
        verify(pacienteRepository).findAll();
    }

    @Test
    void deveBuscarPacientePorId() {
        Paciente paciente = Paciente.builder().id(1L).nome("Carlos").build();

        when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Paciente resultado = pacienteService.buscarPorId(1L);

        assertEquals("Carlos", resultado.getNome());
        verify(pacienteRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoEncontrado() {
        when(pacienteRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            pacienteService.buscarPorId(99L);
        });

        assertTrue(excecao.getMessage().contains("Paciente não encontrado"));
    }

    @Test
    void deveDeletarPaciente() {
        Long id = 5L;

        pacienteService.deletar(id);

        verify(pacienteRepository).deleteById(id);
    }
}

