package br.com.bigsupermercados.entrega.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;

public class GuiaLiberarForm {

	@NotEmpty(message = "Pelo menos uma guia deve ser selecionada")
	private List<Guia> guias;

	@NotNull(message = "Um motorista deve ser selecionada para que as guias sejam liberadas")
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
