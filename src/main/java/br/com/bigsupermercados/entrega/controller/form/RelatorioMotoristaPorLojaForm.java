package br.com.bigsupermercados.entrega.controller.form;

import java.time.LocalDate;

public class RelatorioMotoristaPorLojaForm {

	private Long motorista;
	private LocalDate dataInicial;
	private LocalDate dataFinal;

	public Long getMotorista() {
		return motorista;
	}

	public void setMotorista(Long motorista) {
		this.motorista = motorista;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}
}
