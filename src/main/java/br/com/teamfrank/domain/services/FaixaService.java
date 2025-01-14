package br.com.teamfrank.domain.services;

import java.text.ParseException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.models.dtos.FaixaRequest;
import br.com.teamfrank.domain.models.dtos.FaixaResponse;
import br.com.teamfrank.domain.models.entities.Faixa;
import br.com.teamfrank.infrastructure.repositories.FaixaRepository;

@Service
public class FaixaService {
	
	@Autowired FaixaRepository faixaRepository;
	
	public FaixaResponse cadastrarFaixa(FaixaRequest request) throws ParseException {
	    Faixa faixa = new Faixa();
	    
	    faixa.setId(UUID.randomUUID());
	    faixa.setCor(request.getCor());
	    faixa.setNivel(request.getNivel());
	    faixa.setDataAquisicao(request.getDataAquisicao());
	    		// Adiciona a data de aquisição

	    faixaRepository.save(faixa);

	    var response = new FaixaResponse();
	    response.setCor(faixa.getCor());
	    response.setNivel(faixa.getNivel());
	    response.setDataAquisicao(faixa.getDataAquisicao());  // Garante que o retorno inclui a data
	    response.setId(faixa.getId());

	    return response;
	}
	
	public void alterarFaixa(UUID id, FaixaRequest request)  throws ParseException{
		var faixa = faixaRepository.findById(id).get();
		faixa.setCor(request.getCor());
		faixa.setNivel(request.getNivel());
		 faixa.setDataAquisicao(request.getDataAquisicao());  // Altera a data de aquisição);
		faixaRepository.save(faixa);
	}
	
	
	public FaixaResponse buscarFaixa(UUID id) {
		var faixa = faixaRepository.findById(id).get();
		var response = new FaixaResponse();
		response.setCor(faixa.getCor());
		response.setNivel(faixa.getNivel());
		response.setDataAquisicao(faixa.getDataAquisicao());
		response.setId(faixa.getId());

		return response;
	}

	
}
