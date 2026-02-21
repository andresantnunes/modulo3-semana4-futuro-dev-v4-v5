package com.example.demo;


import com.example.demo.dto.TarefaResponse;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRespository;
import com.example.demo.service.TarefaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

// MockitoExtension é uma extensão que adicion o Mockito
// Mockito permite criar mocks, ou objetos que simulam outros objetos
// Permite teste de métodos sem o contexto do Spring
@ExtendWith(MockitoExtension.class)
public class TarefaServiceTeste {

    @Mock
    private TarefaRespository tarefaRespository;
    // Mock do repositório, simula o comportamento do repositório real

    @InjectMocks
    private TarefaService tarefaService;
    // Injeta o mock do repositório no serviço,
    // permitindo testar o serviço sem o banco de dados real

    @Test // parte do Junit
    public void testBuscarTarefas(){
        // modelo BDD - Behavior Driven Development
        // given - Dados para o teste
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

        // (separado)when do mockito
//        when(tarefaRespository.findAll()).thenReturn(tarefas);
        given(tarefaRespository.findAll())
                .willReturn(tarefas);

        // when - momento da execução do metodo a ser testado
        List<TarefaResponse> resposta = tarefaService.buscarTarefas();

        // then
        assertEquals(tarefas.size(), resposta.size());
        assertEquals(tarefas.getFirst().getId(), resposta.getFirst().id());
        assertEquals(tarefas.get(1).getDescricao(), resposta.get(1).descricao());
    }

}
