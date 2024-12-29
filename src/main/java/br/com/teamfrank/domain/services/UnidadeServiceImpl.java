package br.com.teamfrank.domain.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.contracts.services.UnidadeService;
import br.com.teamfrank.domain.models.dtos.UnidadeRequest;
import br.com.teamfrank.domain.models.dtos.UnidadeResponse;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;

@Service
public class UnidadeServiceImpl implements UnidadeService {

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Override
	public UnidadeResponse cadastrarUnidade(UnidadeRequest request) {
		var unidade = new Unidade();
		
		unidade.setId(UUID.randomUUID());
		unidade.setNome(request.getNome());
		unidadeRepository.save(unidade);
		
		var response = new UnidadeResponse();
		
		response.setId(unidade.getId());
		response.setNome(unidade.getNome());
		return response;
	}

	@Override
	public UnidadeResponse alterarUnidade(UUID id, UnidadeRequest request) {
		// Lógica de alteração de unidade
		return new UnidadeResponse();
	}

}
