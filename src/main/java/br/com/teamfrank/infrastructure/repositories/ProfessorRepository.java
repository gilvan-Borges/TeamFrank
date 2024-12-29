package br.com.teamfrank.infrastructure.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teamfrank.domain.models.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {

}