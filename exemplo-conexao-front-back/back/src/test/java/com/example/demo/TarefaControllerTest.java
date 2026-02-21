package com.example.demo;

import com.example.demo.controller.TarefaController;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRespository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // carrega o contexto do Spring para os testes
@AutoConfigureMockMvc // configura o MockMvc para testes de controladores
public class TarefaControllerTest {
    @Autowired
    TarefaController tarefaController;

    @MockitoBean
    TarefaRespository tarefaRespository;

    @Autowired
    MockMvc mockMvc;

    @Test // parte do Junit
    public void testBuscarTarefas() throws Exception {
        // modelo BDD - Behavior Driven Development
        List<Tarefa> tarefas = List.of(
                new Tarefa(1L,
                        "Tarefa 1",
                        "Descrição 1",
                        false,
                        List.of("tag1", "tag2")),
                new Tarefa(2L,
                        "Tarefa 2",
                        "Descrição 2",
                        true,
                        List.of("tag3"))
        );

        given(tarefaRespository.findAll())
                .willReturn(tarefas);

        // when - momento da execução do metodo a ser testado

        mockMvc
                .perform(get("/tarefas"))
                .andExpect(status().isOk())
                // $ raíz do Json
                // $.campos - para acessar os campos do Json
                .andExpect(jsonPath("$[0].id").value(tarefas.getFirst().getId()))
                .andExpect(jsonPath("$[1].titulo").value(tarefas.get(1).getTitulo()));
        ;
    }
}
