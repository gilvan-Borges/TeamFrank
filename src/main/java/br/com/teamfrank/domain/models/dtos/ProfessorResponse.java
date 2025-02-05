package br.com.teamfrank.domain.models.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.teamfrank.domain.models.entities.Aluno;
import lombok.Data;

@Data
public class ProfessorResponse {
    private UUID id;
    private String nome;
    private UUID enderecoId;
    private String sexo;
    private String cpf;
    private List<String> unidades;  // Lista de nomes ou IDs das unidades
    private List<String> alunos;
    
    private List<String> faixas;

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = (alunos != null) ? alunos.stream()
            .map(Aluno::getNome)
            .collect(Collectors.toList()) 
            : new ArrayList<>();  // Inicializa como lista vazia
    }
    
	public void setUnidades(List<String> unidades) {
		this.unidades = (unidades != null) ? unidades : new ArrayList<>(); // Inicializa como lista vazia
	}
	
	private String mensagem;

}
