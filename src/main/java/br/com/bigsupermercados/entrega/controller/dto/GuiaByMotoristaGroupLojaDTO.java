package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;

public class GuiaByMotoristaGroupLojaDTO {

	private String loja;
	private BigDecimal valorBruto;
	private BigDecimal valorLiquido;

	public String getLoja() {
		return loja;
	}

	public BigDecimal getValorBruto() {
		return valorBruto;
	}

	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}

	public GuiaByMotoristaGroupLojaDTO(Guia guia) {
		super();
		this.loja = guia.getLoja().getApelido();
		this.valorBruto = guia.getValor();
		this.valorLiquido = guia.getValorAReceber();
	}

	public static void converter(List<Guia> guias){
		guias.stream().collect(Collectors.groupingBy(Guia::getLoja)).entrySet().stream();
	}
}
