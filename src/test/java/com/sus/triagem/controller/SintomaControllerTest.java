
package com.sus.triagem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.sus.triagem.application.service.SintomaService;
import com.sus.triagem.domain.model.Sintoma;
import com.sus.triagem.infrastructure.controller.SintomaController;

class SintomaControllerTest {

    @Test
    void deveListarSintomas() {
        SintomaService service = mock(SintomaService.class);
        SintomaController controller = new SintomaController(service);

        when(service.listarTodos()).thenReturn(List.of(
                Sintoma.builder().descricao("Tosse").build()
        ));

        ResponseEntity<List<Sintoma>> response = controller.listarTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Tosse", response.getBody().get(0).getDescricao());
    }
}
