package br.com.teamfrank.application.controllers;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teamfrank.domain.models.entities.TipoAluno;


@RestController
@RequestMapping("/api/tipos")
public class TiposController {
	@GetMapping
	public List<String> getAll() {
		
		//converter os itens do enum em uma lista de string
		return Arrays.stream(TipoAluno.values())
				.map(Enum::name)
				.toList();
	}
}