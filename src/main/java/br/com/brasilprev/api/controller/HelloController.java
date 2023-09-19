package br.com.brasilprev.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/gestao")
    public String exemplo() {
        return "Exemplo de API com Swagger";
    }
}
