package br.com.teamfrank.domain.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.contracts.services.EnderecoService;
import br.com.teamfrank.domain.models.dtos.EnderecoRequest;
import br.com.teamfrank.domain.models.dtos.EnderecoResponse;
import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;

@Service
class EnderecoServiceImpl implements EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public EnderecoResponse cadastrarEndereco(EnderecoRequest request) {
    	
    	 Endereco endereco = new Endereco();
         endereco.setId(UUID.randomUUID());
         endereco.setCep(request.getCep());
         endereco.setLogradouro(request.getLogradouro());
         endereco.setNumero(request.getNumero());
         endereco.setComplemento(request.getComplemento());
         endereco.setBairro(request.getBairro());
         endereco.setCidade(request.getCidade());
         endereco.setUf(request.getUf());
         enderecoRepository.save(endereco);

         EnderecoResponse response = new EnderecoResponse();
         response.setId(endereco.getId());
         response.setLogradouro(endereco.getLogradouro());
         response.setCidade(endereco.getCidade());
         response.setBairro(endereco.getBairro());
         response.setCep(endereco.getCep());
         response.setComplemento(endereco.getComplemento());
         response.setNumero(endereco.getNumero());
         response.setUf(endereco.getUf());
         return response;
    }

    @Override
    public EnderecoResponse alterarEndereco(UUID id, EnderecoRequest request) {
    	
		Endereco endereco = enderecoRepository.findById(id).get();
		endereco.setCep(request.getCep());
		endereco.setLogradouro(request.getLogradouro());
		endereco.setNumero(request.getNumero());
		endereco.setComplemento(request.getComplemento());
		endereco.setBairro(request.getBairro());
		endereco.setCidade(request.getCidade());
		endereco.setUf(request.getUf());
		enderecoRepository.save(endereco);

		EnderecoResponse response = new EnderecoResponse();
		response.setId(endereco.getId());
		response.setLogradouro(endereco.getLogradouro());
		response.setCidade(endereco.getCidade());
		response.setBairro(endereco.getBairro());
		response.setCep(endereco.getCep());
		response.setComplemento(endereco.getComplemento());
		response.setNumero(endereco.getNumero());
		response.setUf(endereco.getUf());
		return response;
    }

	@Override
	public EnderecoResponse deletarEndereco(UUID id) {
		Endereco endereco = enderecoRepository.findById(id).get();
 
        enderecoRepository.delete(endereco);

        EnderecoResponse response = new EnderecoResponse();
        response.setId(endereco.getId());
        response.setLogradouro(endereco.getLogradouro());
        response.setCidade(endereco.getCidade());
        response.setBairro(endereco.getBairro());
        response.setCep(endereco.getCep());
        response.setComplemento(endereco.getComplemento());
        response.setNumero(endereco.getNumero());
        response.setUf(endereco.getUf());
        return response;
    }
	
	@Override
	public EnderecoResponse buscarEndereco(UUID id) {
		Endereco endereco = enderecoRepository.findById(id).get();

		EnderecoResponse response = new EnderecoResponse();
		response.setId(endereco.getId());
		response.setLogradouro(endereco.getLogradouro());
		response.setCidade(endereco.getCidade());
		response.setBairro(endereco.getBairro());
		response.setCep(endereco.getCep());
		response.setComplemento(endereco.getComplemento());
		response.setNumero(endereco.getNumero());
		response.setUf(endereco.getUf());
		return response;
	}
}

 

