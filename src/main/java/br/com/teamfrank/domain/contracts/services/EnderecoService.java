package br.com.teamfrank.domain.contracts.services;

import java.util.UUID;

import br.com.teamfrank.domain.models.dtos.EnderecoRequest;
import br.com.teamfrank.domain.models.dtos.EnderecoResponse;

public interface EnderecoService {
	EnderecoResponse cadastrarEndereco(EnderecoRequest request);

	EnderecoResponse alterarEndereco(UUID id, EnderecoRequest request);

	EnderecoResponse inativarEndereco(UUID id);
}
