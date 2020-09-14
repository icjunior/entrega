package br.com.bigsupermercados.entrega.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.form.LancamentoBorderoForm;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;
import br.com.bigsupermercados.entrega.repository.entrega.LancamentoBorderoRepository;
import br.com.bigsupermercados.entrega.repository.entrega.TipoLancamentoRepository;

@Service
public class LancamentoBorderoService {

	@Autowired
	private LancamentoBorderoRepository repository;

	@Autowired
	private Borderos borderoRepository;

	@Autowired
	private TipoLancamentoRepository tipoLancamentoRepository;

	public List<LancamentoBordero> buscarPorBordero(Long bordero) {
		return repository.findByBordero_Codigo(bordero);
	}

	public void gravar(List<LancamentoBorderoForm> lancamentos) {
		List<LancamentoBordero> lancamentosBordero = lancamentos.stream().map(lancamento -> {
			return lancamento.converter(borderoRepository, tipoLancamentoRepository);
		}).collect(Collectors.toList());

		repository.saveAll(lancamentosBordero);
	}
}
