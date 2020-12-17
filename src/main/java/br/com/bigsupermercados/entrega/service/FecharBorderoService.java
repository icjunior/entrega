package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;

@Service
public class FecharBorderoService {

	@Autowired
	private Borderos repository;
	
	@Autowired
	private LancamentoBorderoService lancamentoBorderoService;

	@Transactional
	public Bordero fechar(Long codigo) {
		Bordero bordero = repository.findById(codigo).get();

		BigDecimal valorArredondamento = bordero.getValorArredondamento();

		// gravando o registro de arredondamento
		lancamentoBorderoService.gravarArredondamento(bordero, valorArredondamento);

		// fazer a inserção do registro do arredondamento na tela de lançamentos
		bordero.setAberto(false);

		return bordero;
	}
}
