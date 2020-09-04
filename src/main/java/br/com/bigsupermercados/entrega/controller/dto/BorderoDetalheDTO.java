package br.com.bigsupermercados.entrega.controller.dto;

import java.util.List;
import java.util.Optional;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;

public class BorderoDetalheDTO {

	private Long codigo;
	private String motorista;
	private List<GuiaBorderoDTO> guias;

	public BorderoDetalheDTO(Bordero bordero) {
		this.codigo = bordero.getCodigo();
		this.motorista = bordero.getMotorista().getNome();
		this.guias = GuiaBorderoDTO.converter(bordero.getGuias());
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getMotorista() {
		return motorista;
	}

	public List<GuiaBorderoDTO> getGuias() {
		return guias;
	}

	public static Object converter(Optional<Bordero> bordero) {
		return new BorderoDetalheDTO(bordero.get());
	}
}
