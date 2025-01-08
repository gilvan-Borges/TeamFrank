package br.com.teamfrank.domain.models.entities;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Unidade {
    @Id
    private UUID id;
    private String nome;
    
    @OneToMany(mappedBy = "unidade", fetch = FetchType.LAZY)
    private Set<Aluno> alunos;

    @ManyToOne
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "unidade", fetch = FetchType.LAZY)
    private Set<Professor> professores;
    

}