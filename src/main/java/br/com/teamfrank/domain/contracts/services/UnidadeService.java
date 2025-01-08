package br.com.teamfrank.domain.contracts.services;

import java.util.List;
import java.util.UUID;

import br.com.teamfrank.domain.models.dtos.UnidadeRequest;
import br.com.teamfrank.domain.models.dtos.UnidadeResponse;
public interface UnidadeService {
    UnidadeResponse cadastrarUnidade(UnidadeRequest request);
    UnidadeResponse alterarUnidade(UUID id, UnidadeRequest request);
    UnidadeResponse buscarUnidadePorId(UUID id);
    UnidadeResponse deletarUnidade(UUID id);
	List<UnidadeResponse> buscarUnidades();
}
