package br.com.teamfrank.domain.models.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class FaixaRequest {
    private String cor;
    private String nivel;
    private Date dataAquisicao;  // Inclui o campo na requisição
}