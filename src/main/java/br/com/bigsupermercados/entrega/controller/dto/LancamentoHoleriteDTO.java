package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;

public class LancamentoHoleriteDTO {

	private Long codigo;
	private String tipoLancamento;
	private String descricao;
	private BigDecimal valor;

	public LancamentoHoleriteDTO(LancamentoBordero lancamento) {
		this.codigo = lancamento.getCodigo();
		this.tipoLancamento = lancamento.getTipoLancamento().getModoLancamento().getDescricao();
		this.descricao = lancamento.getTipoLancamento().getNome();
		this.valor = lancamento.getValor();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public static List<LancamentoHoleriteDTO> converter(List<LancamentoBordero> lancamentos) {
		return lancamentos.stream().map(LancamentoHoleriteDTO::new).collect(Collectors.toList());
	}
}
