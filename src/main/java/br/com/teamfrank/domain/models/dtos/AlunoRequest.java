package br.com.teamfrank.domain.models.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class AlunoRequest {
    private String nome;
    private String datanasc;
    private String sexo;
    private String cpf;
    private String pai;
    private String mae;
    private String tel;
    private String responsavel;
    private EnderecoRequest endereco;
    private List<FaixaRequest> faixas;
    private UUID unidadeId;
    private UUID professorId;
    
    
    @Data
    public static class FaixaRequest {
        private String cor;
        private String nivel;
        private LocalDate dataAquisicao;
    }
}
