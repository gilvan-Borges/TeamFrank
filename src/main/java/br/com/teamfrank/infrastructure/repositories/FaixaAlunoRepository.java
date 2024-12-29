package br.com.teamfrank.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teamfrank.domain.models.entities.FaixaUsuario;

public interface FaixaAlunoRepository extends JpaRepository<FaixaUsuario, UUID> {
	
    List<FaixaUsuario> findByAluno_Id(UUID alunoId);
    List<FaixaUsuario> findByProfessor_Id(UUID professorId);
}
