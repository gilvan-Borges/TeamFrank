package br.com.teamfrank.domain.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teamfrank.domain.contracts.services.AlunoService;
import br.com.teamfrank.domain.models.dtos.AlunoRequest;
import br.com.teamfrank.domain.models.dtos.AlunoResponse;
import br.com.teamfrank.domain.models.entities.Aluno;
import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.domain.models.entities.Faixa;
import br.com.teamfrank.domain.models.entities.FaixaUsuario;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.AlunoRepository;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;
import br.com.teamfrank.infrastructure.repositories.FaixaAlunoRepository;
import br.com.teamfrank.infrastructure.repositories.FaixaRepository;
import br.com.teamfrank.infrastructure.repositories.ProfessorRepository;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;

@Service
class AlunoServiceImpl implements AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private UnidadeRepository unidadeRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private FaixaAlunoRepository faixaAlunoRepository;
	@Autowired
	private FaixaRepository faixaRepository;

	@Override
	@Transactional
	public AlunoResponse cadastrarAluno(AlunoRequest request) {
		Aluno aluno = new Aluno();
		aluno.setId(UUID.randomUUID());
		aluno.setNome(request.getNome());
		aluno.setDatanasc(request.getDatanasc());
		aluno.setSexo(request.getSexo());
		aluno.setCpf(request.getCpf());
		aluno.setPai(request.getPai());
		aluno.setMae(request.getMae());
		aluno.setResponsavel(request.getResponsavel());
		aluno.setTel(request.getTel());

		// Endereco
		Endereco endereco = enderecoRepository
				.findByCepAndLogradouro(request.getEndereco().getCep(), request.getEndereco().getLogradouro())
				.orElseGet(() -> {
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

		// Unidade
		Unidade unidade = unidadeRepository.findById(request.getUnidadeId())
				.orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

		// Professor
		Professor professor = professorRepository.findById(request.getProfessorId())
				.orElseThrow(() -> new RuntimeException("Professor não encontrado"));

		aluno.setEndereco(endereco);
		aluno.setUnidade(unidade);
		aluno.setProfessor(professor);
		alunoRepository.save(aluno);

		// Cadastro das faixas associadas ao aluno
		if (request.getFaixas() != null && !request.getFaixas().isEmpty()) {
			for (AlunoRequest.FaixaRequest faixaRequest : request.getFaixas()) {
				FaixaUsuario faixaAluno = new FaixaUsuario();
				faixaAluno.setId(UUID.randomUUID());
				faixaAluno.setAluno(aluno);
				faixaAluno.setDataAquisicao(faixaRequest.getDataAquisicao());

				Faixa faixa = faixaRepository.findByCorAndNivel(faixaRequest.getCor(), faixaRequest.getNivel()).orElseGet(() -> {
					Faixa novaFaixa = new Faixa();
					novaFaixa.setId(UUID.randomUUID());
					novaFaixa.setCor(faixaRequest.getCor());

					return faixaRepository.save(novaFaixa);
				});

				faixaAluno.setFaixa(faixa);
				faixaAlunoRepository.save(faixaAluno);
			}
		}

		// Prepara a resposta
		AlunoResponse response = new AlunoResponse();
		response.setId(aluno.getId());
		response.setNome(aluno.getNome());
		response.setDatanasc(aluno.getDatanasc());
		response.setEndereco(endereco.getBairro() + ", " + endereco.getLogradouro());
		response.setUnidade(unidade.getNome());
		response.setProfessor(professor.getNome());

		List<String> faixasCadastradas = faixaAlunoRepository.findByAluno_Id(aluno.getId()).stream()
			    .map(faixaAluno -> faixaAluno.getFaixa().getCor())
			    .collect(Collectors.toList());

		response.setFaixas(faixasCadastradas);

		return response;
	}

	@Override
	public AlunoResponse alterarAluno(UUID id, AlunoRequest request) {
		// Lógica para alterar aluno
		return new AlunoResponse();
	}

	@Override
	public AlunoResponse inativarAluno(UUID id) {
		// Lógica para inativar aluno
		return new AlunoResponse();
	}
}
