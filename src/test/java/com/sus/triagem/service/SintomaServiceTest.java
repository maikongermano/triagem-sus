
package com.sus.triagem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sus.triagem.domain.model.Sintoma;
import com.sus.triagem.domain.repository.SintomaRepository;
import com.sus.triagem.infrastructure.service.SintomaServiceImpl;

@ExtendWith(MockitoExtension.class)
class SintomaServiceTest {

    @Mock
    private SintomaRepository sintomaRepository;

    @InjectMocks
    private SintomaServiceImpl sintomaService;

    @Test
    void deveSalvarSintoma() {
        Sintoma sintoma = Sintoma.builder().descricao("Dor no peito").build();
        when(sintomaRepository.save(any(Sintoma.class))).thenReturn(sintoma);

        Sintoma salvo = sintomaService.salvar(sintoma);

        assertEquals("Dor no peito", salvo.getDescricao());
    }

    @Test
    void deveListarTodosSintomas() {
        List<Sintoma> lista = List.of(
                Sintoma.builder().descricao("Febre").build(),
                Sintoma.builder().descricao("Tosse").build()
        );

        when(sintomaRepository.findAll()).thenReturn(lista);

        List<Sintoma> resultado = sintomaService.listarTodos();

        assertEquals(2, resultado.size());
    }
}
