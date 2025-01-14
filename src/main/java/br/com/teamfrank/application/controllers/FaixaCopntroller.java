package br.com.teamfrank.application.controllers;

import java.text.ParseException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamfrank.domain.models.dtos.FaixaRequest;
import br.com.teamfrank.domain.models.dtos.FaixaResponse;
import br.com.teamfrank.domain.services.FaixaService;

@RestController
@RequestMapping("/api/faixas")
public class FaixaCopntroller {
	@Autowired FaixaService faixaService;
	
	
	@PostMapping
	public FaixaResponse cadastrarFaixa(@RequestBody FaixaRequest request) throws ParseException {
		return faixaService.cadastrarFaixa(request);

	}
	
	@PutMapping("/{id}")
	public void alterarFaixa(@PathVariable UUID id, @RequestBody FaixaRequest request) throws ParseException {
		faixaService.alterarFaixa(id, request);
	}
	
	@GetMapping("/{id}")
	public FaixaResponse buscarFaixa(@PathVariable UUID id) {
		return faixaService.buscarFaixa(id);
	}
}
