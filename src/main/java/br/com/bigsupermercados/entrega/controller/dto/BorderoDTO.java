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

	public BorderoDTO(Bordero bordero) {
		this.codigo = bordero.getCodigo();
		this.motorista = bordero.getMotorista().getNome();
		this.dataHoraLancamento = bordero.getDataHoraLancamento();
		this.aberto = bordero.isAberto();
		this.valor = bordero.getGuias().stream().map(guia -> guia.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);

		this.valorAReceber = bordero
				.getGuias().stream().map(guia -> guia.getValor().multiply(guia.getPorcentagem())
						.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		this.quantidadeLancadas = bordero.getGuias().size();
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

	public static List<BorderoDTO> converter(List<Bordero> borderos) {
		return borderos.stream().map(BorderoDTO::new).collect(Collectors.toList());
	}
}
