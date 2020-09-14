package br.com.bigsupermercados.entrega.controller.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;
import br.com.bigsupermercados.entrega.repository.entrega.Borderos;
import br.com.bigsupermercados.entrega.repository.entrega.TipoLancamentoRepository;

public class LancamentoBorderoForm {

	public Long tipoLancamento;
	public Long bordero;
	public BigDecimal valor;

	public Long getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(Long tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public Long getBordero() {
		return bordero;
	}

	public void setBordero(Long bordero) {
		this.bordero = bordero;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LancamentoBordero converter(Borderos borderoRepository, TipoLancamentoRepository tipoLancamentoRepository) {
		Bordero bordero = borderoRepository.getOne(this.bordero);
		TipoLancamento tipoLancamento = tipoLancamentoRepository.getOne(this.tipoLancamento);
		return new LancamentoBordero(bordero, tipoLancamento, valor, LocalDateTime.now());
	}
}
