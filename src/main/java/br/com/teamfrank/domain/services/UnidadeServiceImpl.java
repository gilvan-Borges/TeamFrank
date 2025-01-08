package br.com.teamfrank.domain.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.contracts.services.UnidadeService;
import br.com.teamfrank.domain.models.dtos.UnidadeRequest;
import br.com.teamfrank.domain.models.dtos.UnidadeResponse;
import br.com.teamfrank.domain.models.entities.Aluno;
import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;

@Service
public class UnidadeServiceImpl implements UnidadeService {

    @Autowired 
    UnidadeRepository unidadeRepository;

    @Autowired 
    EnderecoRepository enderecoRepository;

    @Override
    public UnidadeResponse cadastrarUnidade(UnidadeRequest request) {
        Endereco endereco = enderecoRepository.findById(request.getEnderecoId())
            .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        var unidade = new Unidade();
        unidade.setId(UUID.randomUUID());
        unidade.setNome(request.getNome());
        unidade.setEndereco(endereco);
        unidadeRepository.save(unidade);

        var response = new UnidadeResponse();
        response.setId(unidade.getId());
        response.setNome(unidade.getNome());
        response.setEnderecoId(unidade.getEndereco().getId());

        return response;
    }

    @Override
    public UnidadeResponse alterarUnidade(UUID id, UnidadeRequest request) {
        // Lógica de alteração de unidade
        return new UnidadeResponse();
    }

    public UnidadeResponse buscarUnidadePorId(UUID id) {
        // 1. Buscar Unidade sem alunos e professores
        Unidade unidade = unidadeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        UnidadeResponse response = new UnidadeResponse();
        response.setId(unidade.getId());
        response.setNome(unidade.getNome());

        // 2. Buscar alunos separadamente
        List<Aluno> alunos = unidadeRepository.findAlunosByUnidadeId(id);
        List<String> nomesAlunos = alunos.stream()
            .map(Aluno::getNome)
            .collect(Collectors.toList());
        response.setAlunos(nomesAlunos);

        // 3. Buscar professores separadamente
        List<Professor> professores = unidadeRepository.findProfessoresByUnidadeId(id);
        List<String> nomesProfessores = professores.stream()
            .map(Professor::getNome)
            .collect(Collectors.toList());
        response.setProfessores(nomesProfessores);

        return response;
    }

    public List<UnidadeResponse> buscarUnidades() {
        var unidades = unidadeRepository.findAll();
        var response = new ArrayList<UnidadeResponse>();

        for (var unidade : unidades) {
            var unidadeResponse = new UnidadeResponse();
            unidadeResponse.setId(unidade.getId());
            unidadeResponse.setNome(unidade.getNome());

            if (unidade.getEndereco() != null) {
                unidadeResponse.setEnderecoId(unidade.getEndereco().getId());
            } else {
                unidadeResponse.setMensagem("Endereço não encontrado");
            }

            unidadeResponse.setAlunos(Collections.emptyList());
            unidadeResponse.setProfessores(Collections.emptyList());

            response.add(unidadeResponse);
        }
        return response;
    }

    @Override
    public UnidadeResponse deletarUnidade(UUID id) {
        var unidade = unidadeRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Unidade não encontrada"));
        unidadeRepository.delete(unidade);
        return new UnidadeResponse();
    }
}

