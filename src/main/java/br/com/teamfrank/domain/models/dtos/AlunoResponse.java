package br.com.teamfrank.domain.models.dtos;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class AlunoResponse {
    private UUID id;
    private String nome;
    private String datanasc;
    private String sexo;
    private String cpf;
    private String pai;
    private String mae;
    private String tel;
    private String responsavel;
    private String endereco;
    private String unidade;
    private String professor;
    private String mensagem;
    
    private List<String> faixas;
}
