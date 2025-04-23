
package com.sus.triagem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.sus.triagem.application.service.TriagemService;
import com.sus.triagem.domain.model.Triagem;
import com.sus.triagem.infrastructure.controller.TriagemController;

class TriagemControllerTest {

    @Test
    void deveListarTriagensPorPrioridade() {
        TriagemService service = mock(TriagemService.class);
        TriagemController controller = new TriagemController(service);

        Triagem triagemMock = Triagem.builder().prioridade("EMERGENCIA").build();
        when(service.listarPorPrioridade()).thenReturn(List.of(triagemMock));

        ResponseEntity<List<Triagem>> response = controller.listarPorPrioridade();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("EMERGENCIA", response.getBody().get(0).getPrioridade());
    }
}
