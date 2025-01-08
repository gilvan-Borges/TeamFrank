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

import br.com.teamfrank.domain.contracts.services.AlunoService;
import br.com.teamfrank.domain.models.dtos.AlunoRequest;
import br.com.teamfrank.domain.models.dtos.AlunoResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/alunos")
class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public AlunoResponse cadastrarAluno(@Valid @RequestBody AlunoRequest request) {
        return alunoService.cadastrarAluno(request);
    }
    
    @GetMapping
	public List<AlunoResponse> buscarAlunos() {
		return alunoService.buscarAlunos();
	}
    
    @GetMapping("/{id}")
        public AlunoResponse buscarAlunoPorId(@PathVariable UUID id) {
    	            return alunoService.buscarAlunoPorId(id);
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

