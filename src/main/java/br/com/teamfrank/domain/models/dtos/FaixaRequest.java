package br.com.teamfrank.domain.models.dtos;

import lombok.Data;

@Data
public class FaixaRequest {
    private String cor;
    private String nivel;
    private String dataAquisicao;  // Inclui o campo na requisição
}