package br.com.teamfrank.domain.models.dtos;

import java.util.Date;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunoRequest {
    private String nome;
    private Date datanasc;
    private String sexo;
    private String cpf;
    private String pai;
    private String mae;
    private String telefone;
    private String tipo;
    private String responsavel;


    @NotNull(message = "Endereço é obrigatório")
    private UUID enderecoId;

    private UUID unidadeId;

    private UUID professorId;

    @NotNull(message = "Faixa é obrigatória")
    private UUID faixaId;
    

}
