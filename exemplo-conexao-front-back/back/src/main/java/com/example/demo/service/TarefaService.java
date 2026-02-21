package com.example.demo.service;

import com.example.demo.dto.CriarTarefaRequest;
import com.example.demo.dto.TarefaResponse;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRespository tarefaRespository;

    public List<TarefaResponse> buscarTarefas() {
        List<Tarefa> tarefas = tarefaRespository.findAll();
        log.info("Encontradas {} tarefas", tarefas.size());
        List<TarefaResponse> tarefaResponses = tarefas.stream()
                .map(tarefa -> new TarefaResponse(
                        tarefa.getId(),
                        tarefa.getTitulo(),
                        tarefa.getDescricao(),
                        tarefa.getTags(),
                        tarefa.isConcluida()
                ))
                .toList();

        return tarefaResponses;
    }

    public TarefaResponse criarTarefa(CriarTarefaRequest request) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setTags(request.tags());
        Tarefa tarefaSalva = tarefaRespository.save(tarefa);

        TarefaResponse tarefaResponse = new TarefaResponse(
                tarefaSalva.getId(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.getTags(),
                tarefaSalva.isConcluida()
        );

        log.info("Tarefa criada com ID: {}", tarefaSalva.getId());
        return tarefaResponse;
    }

    public TarefaResponse atualizarTarefa(Long id) {
        Tarefa tarefa = tarefaRespository.findById(id).orElse(null);

        if (tarefa == null) {
            throw new RuntimeException("Tarefa não encontrada");
        }

        tarefa.setConcluida(true);
        Tarefa tarefaSalva = tarefaRespository.save(tarefa);

        TarefaResponse tarefaResponse = new TarefaResponse(
                tarefaSalva.getId(),
                tarefaSalva.getTitulo(),
                tarefaSalva.getDescricao(),
                tarefaSalva.getTags(),
                tarefaSalva.isConcluida()
        );

        log.info("Tarefa criada com ID: {}", tarefaSalva.getId());
        return tarefaResponse;
    }

    public void deletarTarefa(Long id) {
        tarefaRespository.deleteById(id);
        log.info("Tarefa deletada com ID: {}", id);
    }
}
