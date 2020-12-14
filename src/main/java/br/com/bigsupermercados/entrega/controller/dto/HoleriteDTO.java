package br.com.bigsupermercados.entrega.controller.dto;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;

public class HoleriteDTO {

	private String loja;
	private String cnpj;
	private Long matricula;
	private String motorista;
	private Long bordero;

	public HoleriteDTO(Bordero bordero) {
		this.loja = bordero.getMotorista().getLoja().getRazao();
		this.cnpj = bordero.getMotorista().getLoja().getCnpj();
		this.matricula = bordero.getMotorista().getCodigo();
		this.motorista = bordero.getMotorista().getNome();
		this.bordero = bordero.getCodigo();
	}

	public String getLoja() {
		return loja;
	}

	public String getCnpj() {
		return cnpj;
	}

	public Long getMatricula() {
		return matricula;
	}

	public String getMotorista() {
		return motorista;
	}

	public Long getBordero() {
		return bordero;
	}

	public HoleriteDTO converter(Bordero bordero) {
		return new HoleriteDTO(bordero);
	}
}
