package br.com.teamfrank.domain.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class UnidadeResponse {
	  	private UUID id;
	    private String nome;
	    private String endereco;
	    private UUID professorId;
	    private UUID alunoId;
	    private String mensagem;
}
