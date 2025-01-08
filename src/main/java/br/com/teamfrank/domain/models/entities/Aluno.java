package br.com.teamfrank.domain.models.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Aluno {
    @Id
    private UUID id;
    private String nome;
    private Date datanasc;
    private String sexo;
    private String cpf;
    private String pai;
    private String mae;
    private String telefone;
    private String responsavel;
    private TipoAluno tipo;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private Unidade unidade;

    @ManyToOne(cascade = CascadeType.PERSIST)  // Garante que Professor seja salvo automaticamente
    @JoinColumn(name = "professor_id")
    private Professor professor;
    
    @OneToOne
    @JoinColumn(name = "faixa_id", referencedColumnName = "id")
    private Faixa faixa;

}