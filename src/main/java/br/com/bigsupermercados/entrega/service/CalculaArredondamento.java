package br.com.bigsupermercados.entrega.service;

import java.math.BigDecimal;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;

public interface CalculaArredondamento {

	public void gravarArredondamento(Bordero bordero, BigDecimal valor);
	
	public void gravarArredondamentoAnterior(Bordero bordero, BigDecimal valor);
}
