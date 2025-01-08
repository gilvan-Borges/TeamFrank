package br.com.teamfrank.domain.contracts.components;

import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import br.com.teamfrank.domain.models.entities.Endereco;
import br.com.teamfrank.domain.models.entities.Faixa;
import br.com.teamfrank.domain.models.entities.Professor;
import br.com.teamfrank.domain.models.entities.Unidade;
import br.com.teamfrank.infrastructure.repositories.EnderecoRepository;
import br.com.teamfrank.infrastructure.repositories.FaixaRepository;
import br.com.teamfrank.infrastructure.repositories.ProfessorRepository;
import br.com.teamfrank.infrastructure.repositories.UnidadeRepository;

@Component
public class LoadDataComponent implements ApplicationRunner {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private UnidadeRepository unidadeRepository;

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private FaixaRepository faixaRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// Criação do endereço
		Endereco endereco = new Endereco();
		endereco.setId(UUID.fromString("b085ee40-e0b4-4699-baf8-6d569c2436ea"));
		endereco.setCep("12345678");
		endereco.setLogradouro("Rua Exemplo");
		endereco.setNumero("100");
		endereco.setComplemento("Apto 101");
		endereco.setBairro("Centro");
		endereco.setCidade("Rio de Janeiro");
		endereco.setUf("RJ");
		endereco = enderecoRepository.save(endereco);
		System.out.println("Endereço cadastrado com ID: " + endereco.getId());
		
		//criação da faixa
				Faixa faixa = new Faixa();
				faixa.setId(UUID.fromString("89298b80-d2a0-4285-8cb4-654adb1a49be"));
				faixa.setCor("Faixa Preta");
				faixa.setNivel("1º Dan");
				faixa.setDataAquisicao(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020"));
				faixa = faixaRepository.save(faixa);

		// Criação da unidade
		Unidade unidade = new Unidade();
		unidade.setId(UUID.fromString("cce94720-e173-43b9-9824-649ef812196f"));
		unidade.setNome("Unidade Imbaré");
		unidade.setEndereco(endereco);

		unidade = unidadeRepository.save(unidade);
		System.out.println("Unidade cadastrada com ID: " + unidade.getId());

		// Criação do professor
		Professor professor = new Professor();

		professor.setId(UUID.fromString("5da4c309-dc23-46fa-a01a-be03fa896dde"));
		professor.setNome("Franklin Batista");
		professor.setSexo("M");
		professor.setCpf("12345678901");
		professor.setEndereco(endereco);
		professor.setUnidade(unidade);
		professor.setFaixa(faixa);

		professor = professorRepository.save(professor);
		System.out.println("Professor cadastrado com ID: " + professor.getId());
	}
}