package br.com.teamfrank.domain.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.contracts.services.ProfessorService;
import br.com.teamfrank.domain.models.dtos.ProfessorRequest;
import br.com.teamfrank.domain.models.dtos.ProfessorResponse;
import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.domain.models.entities.Faixa;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.AlunoRepository;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;
import br.com.teamfrank.infrastructure.repositories.FaixaRepository;
import br.com.teamfrank.infrastructure.repositories.ProfessorRepository;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;

@Service
public class ProfessorServiceImpl implements ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	AlunoRepository alunoRepository;
	@Autowired
	UnidadeRepository unidadeRepository;
	@Autowired
	FaixaRepository faixaRepository;

	public ProfessorResponse cadastrarProfessor(ProfessorRequest request) {
		var professor = new Professor();
		professor.setId(UUID.randomUUID());
		professor.setNome(request.getNome());
		professor.setCpf(request.getCpf());
		professor.setSexo(request.getSexo());

		Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
				.orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
		professor.setUnidade(unidade);
		professorRepository.save(professor);

		// Buscar o endereço pelo ID
		Endereco endereco = enderecoRepository.findById(request.getEnderecoId())
				.orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
		
		Faixa faixa = faixaRepository.findById(request.getFaixaId())
				.orElseThrow(() -> new RuntimeException("Faixa não encontrada"));
		
		professor.setFaixa(faixa);
		professor.setEndereco(endereco);
		professorRepository.save(professor);

		// Salvar o professor com as faixas vinculadas
		professorRepository.save(professor);

		// Preencher a resposta
		var response = new ProfessorResponse();
		response.setMensagem("Professor cadastrado com sucesso!");
		return toResponse(professor);
	}

	@Override
	public ProfessorResponse alterarProfessor(UUID id, ProfessorRequest request) {
		// Buscar o professor existente
		var professor = professorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Professor não encontrado"));

		// Atualizar dados do professor
		professor.setNome(request.getNome());
		professor.setCpf(request.getCpf());
		professor.setSexo(request.getSexo());

		// Buscar o endereço pelo ID (não criar um novo)
		Endereco endereco = enderecoRepository.findById(request.getEnderecoId())
				.orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

		// Buscar unidade
		Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
				.orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

		// Atualizar relações
		professor.setEndereco(endereco);
		professor.setUnidade(unidade);

		// Salvar professor atualizado
		professorRepository.save(professor);

		// Responder com mensagem de sucesso
		var response = new ProfessorResponse();
		response.setMensagem("Professor alterado com sucesso!");

		return toResponse(professor);
	}

	@Override
	public ProfessorResponse excluirProfessor(UUID id) {

		// Buscar o professor existente
		var professor = professorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Professor não encontrado"));

		// Excluir professor
		professorRepository.delete(professor);

		// Responder com mensagem de sucesso
		var response = new ProfessorResponse();
		response.setMensagem("Professor excluído com sucesso!");

		return response;
	}

	@Override
	public ProfessorResponse buscarProfessorPorId(UUID id) {
		var professor = professorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Professor não encontrado"));

		return toResponse(professor);
	}

	@Override
	public List<ProfessorResponse> buscarProfessores() {
		var professores = professorRepository.findAll();
		return professores.stream().map(this::toResponse).toList();
	}

	private ProfessorResponse toResponse(Professor professor) {

		var response = new ProfessorResponse();
		response.setId(professor.getId());
		response.setNome(professor.getNome());
		response.setCpf(professor.getCpf());
		response.setSexo(professor.getSexo());
		response.setEnderecoId(professor.getEndereco().getId());
		response.setUnidades(List.of(professor.getUnidade().getNome()));
		response.setAlunos(alunoRepository.findByProfessor(professor.getId()));

		String corFaixa = Optional.ofNullable(professor.getFaixa()).map(Faixa::getCor).orElse("Sem faixa");
		
		response.setFaixas(List.of(corFaixa));

		return response;
	}

}
