package br.com.bigsupermercados.entrega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.repository.entrega.GuiaRepository;

@Service
public class GuiaService {

	@Autowired
	private GuiaRepository repository;

	public void salvar(Guia guia) {
		repository.save(guia);
	}
}
