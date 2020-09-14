package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;

public class LancamentoBorderoDTO {

	private Long codigo;
	private String tipoLancamento;
	private BigDecimal valor;
	private LocalDateTime dataHoraInclusao;

	public LancamentoBorderoDTO(LancamentoBordero lancamentoBordero) {
		this.codigo = lancamentoBordero.getCodigo();
		this.tipoLancamento = lancamentoBordero.getTipoLancamento().getNome();
		this.valor = lancamentoBordero.getValor();
		this.dataHoraInclusao = lancamentoBordero.getDataHoraInclusao();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDateTime getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public static List<LancamentoBorderoDTO> converter(List<LancamentoBordero> lancamentos) {
		return lancamentos.stream().map(LancamentoBorderoDTO::new).collect(Collectors.toList());
	}
}
