package br.com.teamfrank.domain.models.dtos;

import lombok.Data;

@Data
public class EnderecoRequest {
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
}
