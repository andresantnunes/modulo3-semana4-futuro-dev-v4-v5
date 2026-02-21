package com.example.demo.controller;

//adicionar todos os imports
import com.example.demo.dto.CriarTarefaRequest;
import com.example.demo.model.Tarefa;
import com.example.demo.repository.TarefaRespository;
import com.example.demo.service.TarefaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.dto.TarefaResponse;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor

@Slf4j // Permite adicionar o log. ao código, é colocado em cada classe que queremos ter Logs
// Permite criar logs nos níveis DEBUG, INFO, WARNING e ERROR

@CrossOrigin(origins = "*", allowedHeaders = "*")
// Quando uma chamada externa de frontend, podemos ter um cross Origin
// nesse caso estamo liberando todas as origens
// no caso de uma aplicação empresarial, nos limitamos as origin
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping
    public List<TarefaResponse> buscarTarefas() {
        log.info("Buscando todas as tarefas");

        List<TarefaResponse> tarefaResponses  = tarefaService.buscarTarefas();

        return tarefaResponses;
    }

    @PostMapping
    public TarefaResponse criarTarefa(@RequestBody CriarTarefaRequest request) {
        log.info("Criando nova tarefa com título: {}", request.titulo());
        TarefaResponse tarefaResponse = tarefaService.criarTarefa(request);
        return tarefaResponse;
    }

    @PutMapping("/{id}")
    public TarefaResponse atualizarTarefa(@PathVariable Long id) {
        log.info("Atualizando tarefa com ID: {}", id);
        TarefaResponse tarefaSalva = tarefaService.atualizarTarefa(id);
        return tarefaSalva;
    }

    @DeleteMapping("/{id}")
    public void deletarTarefa(@PathVariable Long id) {
        log.info("Deletando tarefa com ID: {}", id);
        tarefaService.deletarTarefa(id);
    }
}
