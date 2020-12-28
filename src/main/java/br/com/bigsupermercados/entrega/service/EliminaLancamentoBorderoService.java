package br.com.bigsupermercados.entrega.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.repository.entrega.LancamentoBorderoRepository;

@Service
public class EliminaLancamentoBorderoService {

	@Autowired
	private LancamentoBorderoRepository repository;

	@Transactional
	public void eliminaLancamento(Long codigo) {
		LancamentoBordero lancamentoBordero = repository.findById(codigo).get();

		repository.delete(lancamentoBordero);
	}
}
