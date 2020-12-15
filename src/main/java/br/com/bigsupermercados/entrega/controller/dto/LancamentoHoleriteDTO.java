package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;
import br.com.bigsupermercados.entrega.modelo.entrega.ModoLancamento;

public class LancamentoHoleriteDTO {

	private Long codigo;
	private String tipoLancamento;
	private String descricao;
	private BigDecimal valorVencimento;
	private BigDecimal valorDesconto;

	public LancamentoHoleriteDTO(LancamentoBordero lancamento) {
		this.codigo = lancamento.getCodigo();
		this.tipoLancamento = lancamento.getTipoLancamento().getModoLancamento().getDescricao();
		this.descricao = lancamento.getTipoLancamento().getNome();
		this.valorVencimento = lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.ACRESCIMO
				? lancamento.getValor()
				: BigDecimal.ZERO;
		this.valorVencimento = lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.DESCONTO
				? lancamento.getValor()
				: BigDecimal.ZERO;
	}

	public LancamentoHoleriteDTO(Long codigo, String tipoLancamento, String descricao, BigDecimal valor) {
		this.codigo = codigo;
		this.tipoLancamento = tipoLancamento;
		this.descricao = descricao;
		this.valorVencimento = valor;
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

	public BigDecimal getValorVencimento() {
		return valorVencimento;
	}

	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	public static List<LancamentoHoleriteDTO> converter(List<LancamentoBordero> lancamentos) {
		return lancamentos.stream().map(LancamentoHoleriteDTO::new).collect(Collectors.toList());
	}
}
