
package com.sus.triagem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.sus.triagem.application.service.PacienteService;
import com.sus.triagem.domain.model.Paciente;
import com.sus.triagem.infrastructure.controller.PacienteController;

class PacienteControllerTest {

    @Test
    void deveListarTodosPacientes() {
        PacienteService service = mock(PacienteService.class);
        PacienteController controller = new PacienteController(service);

        when(service.listarTodos()).thenReturn(List.of(
                Paciente.builder().nome("João").build()
        ));

        ResponseEntity<List<Paciente>> response = controller.listarTodos();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("João", response.getBody().get(0).getNome());
    }
}
