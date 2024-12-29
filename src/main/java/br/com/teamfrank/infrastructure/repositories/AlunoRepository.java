package br.com.teamfrank.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teamfrank.domain.models.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
	
	boolean existsByCpf(String cpf);

}
