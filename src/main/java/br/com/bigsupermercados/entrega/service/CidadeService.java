package br.com.bigsupermercados.entrega.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;
import br.com.bigsupermercados.entrega.repository.entrega.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> lista() {
		return repository.buscar();
	}

	@Transactional
	public void salvar(Cidade cidade) {
		repository.save(cidade);
	}
}
