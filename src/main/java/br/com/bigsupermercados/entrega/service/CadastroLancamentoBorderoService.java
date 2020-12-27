package br.com.bigsupermercados.entrega.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.repository.entrega.LancamentoBorderoRepository;

@Service
public class CadastroLancamentoBorderoService {

	@Autowired
	private LancamentoBorderoRepository repository;

	@Transactional
	public LancamentoBordero cadastrarLancamento(LancamentoBordero lancamento) {
		return repository.save(lancamento);
	}
}
