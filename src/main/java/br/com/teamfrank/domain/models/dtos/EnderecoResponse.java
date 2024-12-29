package br.com.teamfrank.domain.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class EnderecoResponse {
	 	private UUID id;
	 	private String cep;
		private String logradouro;
		private String numero;
		private String complemento;
		private String bairro;
		private String cidade;
		private String uf;
	    
}
