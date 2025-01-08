package br.com.teamfrank.domain.models.dtos;

import java.util.Date;
import java.util.UUID;

@lombok.Data
public class FaixaResponse {
	private UUID id;
	private String cor;
	private String nivel;
	private Date dataAquisicao;
}
