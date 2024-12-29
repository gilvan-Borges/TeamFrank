package br.com.teamfrank.application.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamfrank.domain.contracts.services.EnderecoService;
import br.com.teamfrank.domain.models.dtos.EnderecoRequest;
import br.com.teamfrank.domain.models.dtos.EnderecoResponse;

@RestController
@RequestMapping("/enderecos")
class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public EnderecoResponse cadastrarEndereco(@RequestBody EnderecoRequest request) {
        return enderecoService.cadastrarEndereco(request);
    }

    @PutMapping("/{id}")
    public EnderecoResponse alterarEndereco(@PathVariable UUID id, @RequestBody EnderecoRequest request) {
        return enderecoService.alterarEndereco(id, request);
    }

}
