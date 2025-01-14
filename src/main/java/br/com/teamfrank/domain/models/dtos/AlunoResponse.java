package br.com.teamfrank.domain.models.dtos;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class AlunoResponse {
    private UUID id;
    private String nome;
    private Date datanasc;
    private Integer idade;
    private String sexo;
    private String cpf;
    private String pai;
    private String mae;
    private String telefone;
    private String responsavel;
    private String tipo;
    private EnderecoResponse endereco;
    private String unidade;
    private String professor;
    private String mensagem;
    private FaixaResponse faixa;
    
}
