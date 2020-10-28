package br.com.bigsupermercados.entrega.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bairro;
import br.com.bigsupermercados.entrega.repository.entrega.BairroRepository;

@Service
public class BairroService {

	@Autowired
	private BairroRepository repository;

	public List<Bairro> lista() {
		return repository.buscarBairrosHabilitados();
	}
	
	@Transactional
	public void salvar(Bairro bairro) {
		repository.save(bairro);
	}
}
