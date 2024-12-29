package br.com.teamfrank.application.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamfrank.domain.contracts.services.AlunoService;
import br.com.teamfrank.domain.models.dtos.AlunoRequest;
import br.com.teamfrank.domain.models.dtos.AlunoResponse;

@RestController
@RequestMapping("/alunos")
class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public AlunoResponse cadastrarAluno(@RequestBody AlunoRequest request) {
        return alunoService.cadastrarAluno(request);
    }

    @PutMapping("/{id}")
    public AlunoResponse alterarAluno(@PathVariable UUID id, @RequestBody AlunoRequest request) {
        return alunoService.alterarAluno(id, request);
    }

    @DeleteMapping("/{id}")
    public AlunoResponse inativarAluno(@PathVariable UUID id) {
        return alunoService.inativarAluno(id);
    }

}

