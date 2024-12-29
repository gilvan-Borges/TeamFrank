package br.com.teamfrank.domain.models.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class FaixaUsuario {
	@Id
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "faixa_id", nullable = false)
	private Faixa faixa;

	@ManyToOne
	@JoinColumn(name = "aluno_id", nullable = false)
	private Aluno aluno;

	@ManyToOne
	@JoinColumn(name = "professor_id", nullable = true)
	private Professor professor;

	@Column(nullable = false)
	private LocalDate dataAquisicao;

}
