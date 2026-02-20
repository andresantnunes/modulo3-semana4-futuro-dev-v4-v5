package com.example.s4m3futurov45;

import com.example.s4m3futurov45.service.CalculoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Teste {

    CalculoService calculoService = new CalculoService();

    @Test
    public void tituloTeste(){
        Double resultado = calculoService.somar(1.1,2.0);

        assertEquals(3.1, resultado);
    }
}
