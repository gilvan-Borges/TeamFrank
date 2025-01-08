package br.com.teamfrank.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.teamfrank.domain.models.entities.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, UUID> {
	
	boolean existsByCpf(String cpf);
	
	@Query(value = """
		    SELECT a.* FROM aluno a
		    JOIN professor p ON p.id = a.professor_id
		    WHERE p.id = :id
		""", nativeQuery = true)
		List<Aluno> findByProfessor(@Param("id") UUID professorId);


}
