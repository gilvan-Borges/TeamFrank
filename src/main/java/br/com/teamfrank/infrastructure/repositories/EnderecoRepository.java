package br.com.teamfrank.infrastructure.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teamfrank.domain.models.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    Optional<Endereco> findByCepAndLogradouro(String cep, String logradouro);
}
