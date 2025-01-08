package br.com.teamfrank.application.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamfrank.domain.contracts.services.UnidadeService;
import br.com.teamfrank.domain.models.dtos.UnidadeRequest;
import br.com.teamfrank.domain.models.dtos.UnidadeResponse;

@RestController
@RequestMapping("/api/unidades")
class UnidadeController {
    @Autowired private UnidadeService unidadeService;

    @PostMapping
    public UnidadeResponse cadastrarUnidade(@RequestBody UnidadeRequest request) {
        return unidadeService.cadastrarUnidade(request);
    }

    @PutMapping("/{id}")
    public UnidadeResponse alterarUnidade(@PathVariable UUID id, @RequestBody UnidadeRequest request) {
        return unidadeService.alterarUnidade(id, request);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponse> buscarUnidadePorId(@PathVariable UUID id) {
        UnidadeResponse response = unidadeService.buscarUnidadePorId(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
	public List<UnidadeResponse> buscarUnidades() {
		return unidadeService.buscarUnidades();
	}
    
    @DeleteMapping("/{id}")
	public void deletarUnidade(@PathVariable UUID id) {
		unidadeService.deletarUnidade(id);
	}
    
}
