package br.com.bigsupermercados.entrega.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;

@Service
public class CadastroBorderoService {

	@Autowired
	private Borderos repository;

	@Transactional
	public Bordero criar(Motorista motorista) {
		Bordero bordero = new Bordero(motorista);
		return repository.save(bordero);
	}
}
