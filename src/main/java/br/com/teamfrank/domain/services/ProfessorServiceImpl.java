package br.com.teamfrank.domain.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.contracts.services.ProfessorService;
import br.com.teamfrank.domain.models.dtos.ProfessorRequest;
import br.com.teamfrank.domain.models.dtos.ProfessorResponse;
import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.domain.models.entities.Faixa;
import br.com.teamfrank.domain.models.entities.FaixaUsuario;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.AlunoRepository;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;
import br.com.teamfrank.infrastructure.repositories.FaixaRepository;
import br.com.teamfrank.infrastructure.repositories.ProfessorRepository;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService{
	
	@Autowired ProfessorRepository professorRepository;
	@Autowired EnderecoRepository enderecoRepository;
	@Autowired AlunoRepository alunoRepository;
	@Autowired UnidadeRepository unidadeRepository;
	@Autowired FaixaRepository faixaRepository;

	@Override
    public ProfessorResponse cadastrarProfessor(ProfessorRequest request) {
        var professor = new Professor();
        professor.setId(UUID.randomUUID());
        professor.setNome(request.getNome());
        professor.setCpf(request.getCpf());
        professor.setSexo(request.getSexo());

        // Unidade
        Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        professor.setUnidade(unidade);
        professorRepository.save(professor);

        // Endereco
        Endereco endereco = enderecoRepository.findByCepAndLogradouro(
                request.getEndereco().getCep(), 
                request.getEndereco().getLogradouro()
        ).orElseGet(() -> {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setId(UUID.randomUUID());
            novoEndereco.setCep(request.getEndereco().getCep());
            novoEndereco.setLogradouro(request.getEndereco().getLogradouro());
            novoEndereco.setNumero(request.getEndereco().getNumero());
            novoEndereco.setComplemento(request.getEndereco().getComplemento());
            novoEndereco.setBairro(request.getEndereco().getBairro());
            novoEndereco.setCidade(request.getEndereco().getCidade());
            novoEndereco.setUf(request.getEndereco().getUf());
            return enderecoRepository.save(novoEndereco);
        });

        professor.setEndereco(endereco);
        professorRepository.save(professor);

        if (request.getFaixas() != null && !request.getFaixas().isEmpty()) {
            for (ProfessorRequest.FaixaRequest faixaRequest : request.getFaixas()) {
                Faixa faixa = faixaRepository.findByCorAndNivel(
                    faixaRequest.getCor(), faixaRequest.getNivel()
                ).orElseGet(() -> {
                    Faixa novaFaixa = new Faixa();
                    novaFaixa.setId(UUID.randomUUID());
                    novaFaixa.setCor(faixaRequest.getCor());
                    novaFaixa.setNivel(faixaRequest.getNivel());
                    return faixaRepository.save(novaFaixa);
                });

                // Criando FaixaUsuario para vincular o professor à faixa
                FaixaUsuario faixaUsuario = new FaixaUsuario();
                faixaUsuario.setId(UUID.randomUUID());
                faixaUsuario.setFaixa(faixa);
                faixaUsuario.setProfessor(professor);
                faixaUsuario.setDataAquisicao(faixaRequest.getDataAquisicao());

                professor.getFaixas().add(faixaUsuario);
            }
        }

        professorRepository.save(professor);

        // Preenchendo a resposta
        var response = new ProfessorResponse();
        response.setId(professor.getId());
        response.setNome(professor.getNome());
        response.setEndereco(professor.getEndereco().getCidade());
        response.setEndereco(professor.getEndereco().getBairro());
        response.setEndereco(professor.getEndereco().getLogradouro());
        response.setMensagem("Professor cadastrado com sucesso!");

        return response;
    }

	@Override
	public ProfessorResponse alterarProfessor(UUID id, ProfessorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfessorResponse inativarProfessor(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}


}
