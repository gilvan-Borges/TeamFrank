package br.com.teamfrank.domain.contracts.services;

import java.util.UUID;

import br.com.teamfrank.domain.models.dtos.AlunoRequest;
import br.com.teamfrank.domain.models.dtos.AlunoResponse;

public interface AlunoService {
	AlunoResponse cadastrarAluno(AlunoRequest request);

	AlunoResponse alterarAluno(UUID id, AlunoRequest request);

	AlunoResponse inativarAluno(UUID id);

}
