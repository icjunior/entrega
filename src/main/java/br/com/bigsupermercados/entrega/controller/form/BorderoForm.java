package br.com.bigsupermercados.entrega.controller.form;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Motoristas;

public class BorderoForm {

	private Long codigoMotorista;

	public Long getCodigoMotorista() {
		return codigoMotorista;
	}

	public void setCodigoMotorista(Long codigoMotorista) {
		this.codigoMotorista = codigoMotorista;
	}


	public Bordero converter(Motoristas motoristaRepository) {
		Motorista motorista = motoristaRepository.getOne(this.codigoMotorista);
		return new Bordero(motorista);
	}
}
