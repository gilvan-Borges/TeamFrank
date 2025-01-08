package br.com.teamfrank.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/api/professores")
class buscarProfessores {
    @Autowired
    private ProfessorService professorService;

    @PostMapping
    public ProfessorResponse cadastrarProfessor(@RequestBody ProfessorRequest request) {
        return professorService.cadastrarProfessor(request);
    }
    
   	@GetMapping
   	public List<ProfessorResponse> buscarProfessores() {
   		return professorService.buscarProfessores();
   	}

    @PutMapping("/{id}")
    public ProfessorResponse alterarProfessor(@PathVariable UUID id, @RequestBody ProfessorRequest request) {
        return professorService.alterarProfessor(id, request);
    }

    @DeleteMapping("/{id}")
    public ProfessorResponse excluirProfessor(@PathVariable UUID id) {
        return professorService.excluirProfessor(id);
    }
    
    @GetMapping("/{id}")
	public ProfessorResponse buscarProfessorPorId(@PathVariable UUID id) {
		return professorService.buscarProfessorPorId(id);
	}
   

}
