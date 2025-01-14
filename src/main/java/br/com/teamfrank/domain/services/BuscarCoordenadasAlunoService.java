package br.com.teamfrank.domain.services;

import org.springframework.stereotype.Service;

import br.com.teamfrank.domain.contracts.components.GeocodingService;
import br.com.teamfrank.domain.models.entities.Aluno;
import br.com.teamfrank.domain.models.entities.Coordenada;

@Service
public class BuscarCoordenadasAlunoService {

    private final GeocodingService geocodingService;

    public BuscarCoordenadasAlunoService(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    public Coordenada executar(Aluno aluno) {
        String cep = aluno.getEndereco().getCep();
        String bairro = aluno.getEndereco().getBairro();
        String cidade = aluno.getEndereco().getCidade();
     

        // Validar os campos
        if (cep == null || bairro == null || cidade == null) {
            throw new IllegalArgumentException("Endereço incompleto: Verifique CEP, bairroe  cidade ");
        }

        // Formatar o CEP: XXXXX-XXX
        if (cep.length() == 8) {
            cep = cep.substring(0, 5) + "-" + cep.substring(5);
        }

        // Montar o endereço completo corretamente
        String enderecoCompleto = String.format("%s, %s, %s", cep, bairro, cidade);
        System.out.println("🔎 Buscando coordenadas para: " + enderecoCompleto);

        return geocodingService.buscarCoordenadas(enderecoCompleto);
    }
}