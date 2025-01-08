package br.com.teamfrank.domain.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProfessorRequest {
    private String nome;
    private String sexo;
    private String cpf;
    private UUID enderecoId;
    private UUID unidadeId;
    private UUID faixaId;
   
}