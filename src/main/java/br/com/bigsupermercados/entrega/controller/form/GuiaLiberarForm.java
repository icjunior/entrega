package br.com.bigsupermercados.entrega.controller.form;

import java.util.List;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;

public class GuiaLiberarForm {

	private List<Guia> guias;
	private Motorista motorista;

	public List<Guia> getGuias() {
		return guias;
	}

	public void setGuias(List<Guia> guias) {
		this.guias = guias;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

//	public List<Guia> converter(){
//		return 
//	}
}
