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

	public BorderoDTO(Bordero bordero) {
		this.codigo = bordero.getCodigo();
		this.motorista = bordero.getMotorista().getNome();
		this.dataHoraLancamento = bordero.getDataHoraLancamento();
		this.aberto = bordero.isAberto();
		this.valor = BigDecimal.ZERO;
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

	public static List<BorderoDTO> converter(List<Bordero> borderos) {
		return borderos.stream().map(BorderoDTO::new).collect(Collectors.toList());
	}
}
