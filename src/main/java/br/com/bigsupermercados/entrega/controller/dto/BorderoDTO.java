package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;

public class BorderoDTO {

	private Long codigo;
	private String motorista;
	private LocalDateTime dataHoraLancamento;
	private boolean aberto;
	private BigDecimal valor;
	private Integer quantidadeLancadas;
	private BigDecimal valorAReceber;
	private BigDecimal valorAcrescimos;
	private BigDecimal valorDescontos;
	private BigDecimal valorTotalLiquido;
	private LocalDateTime dataHoraFechamento;

	public BorderoDTO(Bordero bordero) {
		this.codigo = bordero.getCodigo();
		this.motorista = bordero.getMotorista().getNome();
		this.dataHoraLancamento = bordero.getDataHoraLancamento();
		this.aberto = bordero.isAberto();
		this.valor = bordero.getGuias() == null ? BigDecimal.ZERO
				: bordero.getGuias().stream().map(guia -> guia.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
		this.valorAReceber = bordero.getValorAReceberBruto();
		this.valorAcrescimos = bordero.getValorAcrescimo();
		this.valorDescontos = bordero.getValorDescontos();
		this.valorTotalLiquido = bordero.getValorLiquido();
		this.quantidadeLancadas = bordero.getGuias() == null ? 0 : bordero.getGuias().size();
		this.dataHoraFechamento = bordero.getDatahoraFechamento();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getMotorista() {
		return motorista;
	}

	public LocalDateTime getDataHoraLancamento() {
		return dataHoraLancamento;
	}

	public boolean isAberto() {
		return aberto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeLancadas() {
		return quantidadeLancadas;
	}

	public BigDecimal getValorAReceber() {
		return valorAReceber;
	}

	public BigDecimal getValorAcrescimos() {
		return valorAcrescimos;
	}

	public BigDecimal getValorDescontos() {
		return valorDescontos;
	}

	public BigDecimal getValorTotalLiquido() {
		return valorTotalLiquido;
	}

	public LocalDateTime getDataHoraFechamento() {
		return dataHoraFechamento;
	}

	public static List<BorderoDTO> converter(List<Bordero> borderos) {
		return borderos.stream().map(BorderoDTO::new).collect(Collectors.toList());
	}

	public static BorderoDTO converter(Bordero bordero) {
		return new BorderoDTO(bordero);
	}
}
