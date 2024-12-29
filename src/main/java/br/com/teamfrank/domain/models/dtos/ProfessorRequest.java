package br.com.teamfrank.domain.models.dtos;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ProfessorRequest {
    private String nome;
    private String sexo;
    private String cpf;
    private EnderecoRequest endereco;
    private UUID unidadeId;
    private List<FaixaRequest> faixas;
   
    @Data
    public static class FaixaRequest {
        private String cor;
        private String nivel;
        private LocalDate dataAquisicao;
    }
}