package br.com.teamfrank.domain.models.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class UnidadeRequest {
    private String nome;
    private UUID enderecoId;
}
