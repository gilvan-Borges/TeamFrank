package br.com.teamfrank.domain.models.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UnidadeResponse {
	private UUID id;
	private String nome;
	private UUID enderecoId;
	private List<String> alunos;
	private List<String> professores;
	private String mensagem;
}
