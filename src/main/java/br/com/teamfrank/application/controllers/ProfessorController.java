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

import br.com.teamfrank.domain.contracts.services.ProfessorService;
import br.com.teamfrank.domain.models.dtos.ProfessorRequest;
import br.com.teamfrank.domain.models.dtos.ProfessorResponse;

@RestController
@RequestMapping("/professores")
class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ProfessorResponse cadastrarProfessor(@RequestBody ProfessorRequest request) {
        return professorService.cadastrarProfessor(request);
    }

    @PutMapping("/{id}")
    public ProfessorResponse alterarProfessor(@PathVariable UUID id, @RequestBody ProfessorRequest request) {
        return professorService.alterarProfessor(id, request);
    }

    @DeleteMapping("/{id}")
    public ProfessorResponse inativarProfessor(@PathVariable UUID id) {
        return professorService.inativarProfessor(id);
    }

}
