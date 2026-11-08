package br.com.sistemas.chamados.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.sistemas.chamados.service.ChamadoService;

@WebMvcTest(ChamadoController.class)
public class ChamadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChamadoService chamadoService;

    @Test
    public void deveRetornarSucessoAoAcessarPaginaDoControlador() throws Exception {
        mockMvc.perform(get("/chamados"))
                .andExpect(status().isOk());
    }
}