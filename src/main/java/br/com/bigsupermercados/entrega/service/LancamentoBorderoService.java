package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.form.LancamentoBorderoForm;
import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
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

	@Transactional
	public List<LancamentoBordero> gravar(Long codigoBordero, List<LancamentoBorderoForm> form) {
		Bordero bordero = borderoRepository.findById(codigoBordero).get();
		List<LancamentoBordero> lancamentos = form.stream().map((lancamento) -> {
			TipoLancamento tipoLancamento = tipoLancamentoRepository.findById(lancamento.getTipoLancamento()).get();

			return new LancamentoBordero(bordero, tipoLancamento, lancamento.getValor(), LocalDateTime.now());
		}).collect(Collectors.toList());
		return repository.saveAll(lancamentos);
	}

	@Transactional
	public void gravarArredondamento(Bordero bordero, BigDecimal valor) {
		TipoLancamento tipoLancamento = tipoLancamentoRepository.findByNome("Arredondamento");
		LancamentoBordero lancamento = new LancamentoBordero(bordero, tipoLancamento, valor, LocalDateTime.now());
		repository.save(lancamento);
	}

	public void eliminaLancamento(Long codigo) {
		LancamentoBordero lancamentoBordero = repository.findById(codigo).get();

		repository.delete(lancamentoBordero);
	}
}
