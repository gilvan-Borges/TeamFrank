package br.com.teamfrank.domain.contracts.services;

import java.util.UUID;

import br.com.teamfrank.domain.models.dtos.ProfessorRequest;
import br.com.teamfrank.domain.models.dtos.ProfessorResponse;

public interface ProfessorService {
    ProfessorResponse cadastrarProfessor(ProfessorRequest request);
    ProfessorResponse alterarProfessor(UUID id, ProfessorRequest request);
    ProfessorResponse inativarProfessor(UUID id);
}
