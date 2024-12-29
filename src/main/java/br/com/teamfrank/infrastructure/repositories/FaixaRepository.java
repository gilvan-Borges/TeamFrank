package br.com.teamfrank.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.teamfrank.domain.models.entities.Faixa;

public interface FaixaRepository extends JpaRepository<Faixa, UUID> {

	Optional<Faixa> findByCorAndNivel(String cor, String nivel);
}
