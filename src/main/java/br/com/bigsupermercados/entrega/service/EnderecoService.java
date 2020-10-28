package br.com.bigsupermercados.entrega.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;
import br.com.bigsupermercados.entrega.repository.entrega.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public Optional<Endereco> findByCEP(String cep) {
		return repository.findByCep(cep.replace("-", ""));
	}

	public List<Endereco> findByLogradouro(String logradouro, Long cidade) {
		return repository.buscar(logradouro, cidade);
	}

	public List<Endereco> findAll() {
		return repository.buscarPorCidadesAtivas();
	}

	@Transactional
	public void salvar(Endereco endereco) {
		repository.save(endereco);
	}
}
