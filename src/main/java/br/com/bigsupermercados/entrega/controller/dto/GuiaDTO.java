package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;

public class GuiaDTO {

	private Long codigo;
	private LocalDate data;
	private String loja;
	private Integer pdv;
	private String cupom;
	private BigDecimal valor;
	private String cliente;
	private String motorista;
	private boolean validado;

	public GuiaDTO(Guia guia) {
		super();
		this.codigo = guia.getCodigo();
		this.data = guia.getData();
		this.loja = guia.getLoja().getApelido();
		this.pdv = guia.getPdv();
		this.cupom = guia.getCupom();
		this.valor = guia.getValor();
		this.cliente = guia.getCliente().getNome();
		this.motorista = guia.getMotorista() == null ? String.valueOf("Não liberada") : guia.getMotorista().getNome();
		this.validado = guia.isValidado();
	}

	public Long getCodigo() {
		return codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public String getLoja() {
		return loja;
	}

	public Integer getPdv() {
		return pdv;
	}

	public String getCupom() {
		return cupom;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getCliente() {
		return cliente;
	}

	public String getMotorista() {
		return motorista;
	}

	public boolean isValidado() {
		return validado;
	}

	public static Page<GuiaDTO> converter(Page<Guia> guias) {
		return guias.map(GuiaDTO::new);
	}

	public static GuiaDTO converter(Guia guia) {
		return new GuiaDTO(guia);
	}
}
