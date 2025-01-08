package br.com.teamfrank.infrastructure.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.teamfrank.domain.models.entities.Aluno;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, UUID> {

    @Query("SELECT u FROM Unidade u WHERE u.id = :id")
    Optional<Unidade> findById(@Param("id") UUID id);

    // Carregar alunos separadamente
    @Query("SELECT a FROM Aluno a WHERE a.unidade.id = :id")
    List<Aluno> findAlunosByUnidadeId(@Param("id") UUID id);

    // Carregar professores separadamente
    @Query("SELECT p FROM Professor p WHERE p.unidade.id = :id")
    List<Professor> findProfessoresByUnidadeId(@Param("id") UUID id);
}

