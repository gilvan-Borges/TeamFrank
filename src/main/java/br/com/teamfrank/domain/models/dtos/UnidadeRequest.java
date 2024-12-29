package br.com.teamfrank.domain.models.dtos;

import lombok.Data;

@Data
public class UnidadeRequest {
	private String nome;
	private EnderecoRequest endereco;
}
