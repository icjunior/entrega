package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;

public class GuiaBorderoDTO {

	private Long codigo;
	private LocalDate data;
	private String loja;
	private Integer pdv;
	private String cupom;
	private BigDecimal valor;
	private String cliente;
	private String bairro;
	private BigDecimal porcentagem;
	private BigDecimal valorAReceber;
	private boolean validado;

	public GuiaBorderoDTO(Guia guia) {
		super();
		this.codigo = guia.getCodigo();
		this.data = guia.getData();
		this.loja = guia.getLoja().getApelido();
		this.pdv = guia.getPdv();
		this.cupom = guia.getCupom();
		this.valor = guia.getValor();
		this.cliente = guia.getCliente().getNome();
		this.bairro = guia.getBairro();
		this.porcentagem = guia.getPorcentagem();
		this.valorAReceber = calculaValorAReceber(guia.getValor(), guia.getPorcentagem());
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

	public String getBairro() {
		return bairro;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public BigDecimal getValorAReceber() {
		return valorAReceber;
	}

	public boolean isValidado() {
		return validado;
	}

	public static List<GuiaBorderoDTO> converter(List<Guia> guias) {
		return guias.stream().map(GuiaBorderoDTO::new).collect(Collectors.toList());
	}

	private BigDecimal calculaValorAReceber(BigDecimal valor, BigDecimal porcentagem) {
		return valor.multiply(porcentagem).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
	}
}
