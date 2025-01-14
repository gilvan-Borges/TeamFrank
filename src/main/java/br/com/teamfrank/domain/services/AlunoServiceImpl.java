package br.com.teamfrank.domain.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teamfrank.domain.contracts.services.AlunoService;
import br.com.teamfrank.domain.models.dtos.AlunoRequest;
import br.com.teamfrank.domain.models.dtos.AlunoResponse;
import br.com.teamfrank.domain.models.dtos.EnderecoResponse;
import br.com.teamfrank.domain.models.dtos.FaixaResponse;
import br.com.teamfrank.domain.models.entities.Aluno;
import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.domain.models.entities.Faixa;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.TipoAluno;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.AlunoRepository;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;
import br.com.teamfrank.infrastructure.repositories.FaixaRepository;
import br.com.teamfrank.infrastructure.repositories.ProfessorRepository;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;
import jakarta.persistence.EntityNotFoundException;


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
    private FaixaRepository faixaRepository;
    
    
    @Override
    @Transactional
    public AlunoResponse cadastrarAluno(AlunoRequest request) {

        try {
            validarRequest(request);

            Aluno aluno = new Aluno();
            aluno.setId(UUID.randomUUID());
            aluno.setNome(request.getNome());
            aluno.setDatanasc(request.getDatanasc());
            aluno.setSexo(request.getSexo());
            aluno.setCpf(request.getCpf());
            aluno.setPai(request.getPai());
            aluno.setMae(request.getMae());
            aluno.setResponsavel(request.getResponsavel());
            aluno.setTelefone(request.getTelefone());
            aluno.setTipo(TipoAluno.valueOf(request.getTipo()));
            

            // Busca e vincula entidades
            Endereco endereco = findEntityById(request.getEnderecoId(), enderecoRepository, "Endereço");
            Unidade unidade = findEntityById(request.getUnidadeId(), unidadeRepository, "Unidade");
            Professor professor = findEntityById(request.getProfessorId(), professorRepository, "Professor");
            Faixa faixa = findEntityById(request.getFaixaId(), faixaRepository, "Faixa");

            aluno.setEndereco(endereco);
            aluno.setUnidade(unidade);
            aluno.setProfessor(professor);
            aluno.setFaixa(faixa);

            alunoRepository.save(aluno);
            
            var response = new AlunoResponse();
            response.setId(aluno.getId());
            
            response.setNome(aluno.getNome());
            response.setDatanasc(aluno.getDatanasc());
            response.setIdade(calcularIdade(aluno.getDatanasc()));
            response.setCpf(aluno.getCpf());
            response.setPai(aluno.getPai());
            response.setMae(aluno.getMae());
            response.setTelefone(aluno.getTelefone());
            response.setResponsavel(aluno.getResponsavel());
            response.setSexo(aluno.getSexo());
           
            
            
            response.setUnidade(aluno.getUnidade().getNome());
            response.setProfessor(aluno.getProfessor().getNome());
            
            var faixaResponse = new FaixaResponse();
            faixaResponse.setId(aluno.getFaixa().getId());
            faixaResponse.setCor(aluno.getFaixa().getCor());
            faixaResponse.setNivel(aluno.getFaixa().getNivel());
            faixaResponse.setDataAquisicao(aluno.getFaixa().getDataAquisicao());
            
            response.setFaixa(faixaResponse);
            
            response.setMensagem("Aluno cadastrado com sucesso");
            
            return response;
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Falha ao encontrar entidade: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao cadastrar aluno: " + e.getMessage());
        }
    }
    
    
    private void validarRequest(AlunoRequest request) {
        if (request.getEnderecoId() == null) {
            throw new IllegalArgumentException("ID do endereço é obrigatório");
        }
        if (request.getUnidadeId() == null) {
            throw new IllegalArgumentException("ID da unidade é obrigatório");
        }
        if (request.getProfessorId() == null) {
            throw new IllegalArgumentException("ID do professor é obrigatório");
        }
        if (request.getFaixaId() == null) {
            throw new IllegalArgumentException("ID da faixa é obrigatório");
        }
    }

    private <T> T findEntityById(UUID id, JpaRepository<T, UUID> repository, String entityName) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityName + " não encontrado com ID: " + id));
    }


    private Integer calcularIdade(Date dataNascimento) {
        LocalDate dataNasc = dataNascimento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dataNasc, LocalDate.now()).getYears();
    }

    @Override
    @Transactional
    public AlunoResponse alterarAluno(UUID id, AlunoRequest request) {
        var aluno = alunoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Aluno não encontrado para atualização."));

        aluno.setNome(request.getNome());
        aluno.setDatanasc(request.getDatanasc());
        aluno.setSexo(request.getSexo());
        aluno.setTipo(TipoAluno.valueOf(request.getTipo()));
        aluno.setCpf(request.getCpf());
        aluno.setPai(request.getPai());
        aluno.setMae(request.getMae());
        aluno.setTelefone(request.getTelefone());
        aluno.setResponsavel(request.getResponsavel());

        alunoRepository.save(aluno);
        
        var response = new AlunoResponse();
        
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        response.setDatanasc(aluno.getDatanasc());
        response.setIdade(calcularIdade(aluno.getDatanasc()));
        response.setCpf(aluno.getCpf());
        response.setPai(aluno.getPai());
        response.setMae(aluno.getMae());
        response.setTelefone(aluno.getTelefone());
        response.setResponsavel(aluno.getResponsavel());
        response.setSexo(aluno.getSexo());
        
     // Preenche os dados do endereço
        var enderecoResponse = new EnderecoResponse();
        enderecoResponse.setId(aluno.getEndereco().getId());
        enderecoResponse.setCep(aluno.getEndereco().getCep());
        enderecoResponse.setLogradouro(aluno.getEndereco().getLogradouro());
        enderecoResponse.setCidade(aluno.getEndereco().getCidade());
        enderecoResponse.setBairro(aluno.getEndereco().getBairro());
        enderecoResponse.setNumero(aluno.getEndereco().getNumero());
        enderecoResponse.setUf(aluno.getEndereco().getUf());
        enderecoResponse.setComplemento(aluno.getEndereco().getComplemento());
   
        
        response.setEndereco(enderecoResponse);
        
        response.setUnidade(aluno.getUnidade().getNome());
        response.setProfessor(aluno.getProfessor().getNome());
        
        var faixaResponse = new FaixaResponse();
        faixaResponse.setId(aluno.getFaixa().getId());
        faixaResponse.setCor(aluno.getFaixa().getCor());
        faixaResponse.setNivel(aluno.getFaixa().getNivel());
        faixaResponse.setDataAquisicao(aluno.getFaixa().getDataAquisicao());
        
        response.setFaixa(faixaResponse);
        
        
        
        response.setMensagem("Aluno Atualizado com sucesso");
        
        return response;
    }

    @Override
    public AlunoResponse inativarAluno(UUID id) {
        var aluno = alunoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Aluno não encontrado para inativação. Verifique o ID."));

        alunoRepository.delete(aluno);
        var response = new AlunoResponse();
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        response.setDatanasc(aluno.getDatanasc());
        response.setIdade(calcularIdade(aluno.getDatanasc()));
        response.setCpf(aluno.getCpf());
        response.setPai(aluno.getPai());
        response.setMae(aluno.getMae());
        response.setTelefone(aluno.getTelefone());
        response.setResponsavel(aluno.getResponsavel());
        response.setSexo(aluno.getSexo());
        
     // Preenche os dados do endereço
        var enderecoResponse = new EnderecoResponse();
        enderecoResponse.setId(aluno.getEndereco().getId());
        enderecoResponse.setCep(aluno.getEndereco().getCep());
        enderecoResponse.setLogradouro(aluno.getEndereco().getLogradouro());
        enderecoResponse.setCidade(aluno.getEndereco().getCidade());
        enderecoResponse.setBairro(aluno.getEndereco().getBairro());
        enderecoResponse.setNumero(aluno.getEndereco().getNumero());
        enderecoResponse.setUf(aluno.getEndereco().getUf());
        enderecoResponse.setComplemento(aluno.getEndereco().getComplemento());
        
        response.setEndereco(enderecoResponse);
        
        response.setUnidade(aluno.getUnidade().getNome());
        response.setProfessor(aluno.getProfessor().getNome());
        
        var faixaResponse = new FaixaResponse();
        faixaResponse.setId(aluno.getFaixa().getId());
        faixaResponse.setCor(aluno.getFaixa().getCor());
        faixaResponse.setNivel(aluno.getFaixa().getNivel());
        faixaResponse.setDataAquisicao(aluno.getFaixa().getDataAquisicao());
        
        response.setFaixa(faixaResponse);
        
        
        response.setMensagem("Aluno Excluido com sucesso");
        
        return response;
    }

    @Override
    public AlunoResponse buscarAlunoPorId(UUID id) {
        var aluno = alunoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Aluno não encontrado. Verifique o ID."));

        var response = new AlunoResponse();
        response.setId(aluno.getId());
        response.setNome(aluno.getNome());
        response.setDatanasc(aluno.getDatanasc());
        response.setIdade(calcularIdade(aluno.getDatanasc()));
        response.setCpf(aluno.getCpf());
        response.setPai(aluno.getPai());
        response.setMae(aluno.getMae());
        response.setTelefone(aluno.getTelefone());
        response.setResponsavel(aluno.getResponsavel());
        response.setTipo(aluno.getTipo().toString());
        response.setSexo(aluno.getSexo());
        response.setUnidade(aluno.getUnidade().getNome());
        response.setProfessor(aluno.getProfessor().getNome());

        // Preenche os dados do endereço
        
        
     // Mapeia o endereço corretamente
        if (aluno.getEndereco() != null) {
        	var enderecoResponse = new EnderecoResponse();
            enderecoResponse.setId(aluno.getEndereco().getId());
            enderecoResponse.setCep(aluno.getEndereco().getCep());
            enderecoResponse.setLogradouro(aluno.getEndereco().getLogradouro());
            enderecoResponse.setCidade(aluno.getEndereco().getCidade());
            enderecoResponse.setBairro(aluno.getEndereco().getBairro());
            enderecoResponse.setNumero(aluno.getEndereco().getNumero());
            enderecoResponse.setUf(aluno.getEndereco().getUf());
            enderecoResponse.setComplemento(aluno.getEndereco().getComplemento());
            response.setEndereco(enderecoResponse);

        } else {
            response.setEndereco(null);  // Se não houver endereço, mantém null
        }
        
        
        var faixaResponse = new FaixaResponse();
        faixaResponse.setId(aluno.getFaixa().getId());
        faixaResponse.setCor(aluno.getFaixa().getCor());
        faixaResponse.setNivel(aluno.getFaixa().getNivel());
        faixaResponse.setDataAquisicao(aluno.getFaixa().getDataAquisicao());
        
        response.setFaixa(faixaResponse);
        

        return response;
    }


    @Override
    public List<AlunoResponse> buscarAlunos() {
        var alunos = alunoRepository.findAll();
        var lista = new ArrayList<AlunoResponse>();
 
        for (var aluno : alunos) {
        	
        	var response = new AlunoResponse();
            response.setId(aluno.getId());
            response.setNome(aluno.getNome());
            response.setDatanasc(aluno.getDatanasc());
            response.setIdade(calcularIdade(aluno.getDatanasc()));
            response.setCpf(aluno.getCpf());
            response.setPai(aluno.getPai());
            response.setMae(aluno.getMae());
            response.setTelefone(aluno.getTelefone());
            response.setResponsavel(aluno.getResponsavel());
            response.setTipo(aluno.getTipo().name());
            response.setSexo(aluno.getSexo());
         // Preenche os dados do endereço
            var enderecoResponse = new EnderecoResponse();
            enderecoResponse.setId(aluno.getEndereco().getId());
            enderecoResponse.setCep(aluno.getEndereco().getCep());
            enderecoResponse.setLogradouro(aluno.getEndereco().getLogradouro());
            enderecoResponse.setCidade(aluno.getEndereco().getCidade());
            enderecoResponse.setBairro(aluno.getEndereco().getBairro());
            enderecoResponse.setNumero(aluno.getEndereco().getNumero());
            enderecoResponse.setUf(aluno.getEndereco().getUf());
            enderecoResponse.setComplemento(aluno.getEndereco().getComplemento());
            
            response.setEndereco(enderecoResponse);
            response.setUnidade(aluno.getUnidade().getNome());
            response.setProfessor(aluno.getProfessor().getNome());
            
            var faixaResponse = new FaixaResponse();
            faixaResponse.setId(aluno.getFaixa().getId());
            faixaResponse.setCor(aluno.getFaixa().getCor());
            faixaResponse.setNivel(aluno.getFaixa().getNivel());
            faixaResponse.setDataAquisicao(aluno.getFaixa().getDataAquisicao());
            
            response.setFaixa(faixaResponse);
            
            lista.add(response);
        }
        return lista;
    }


}