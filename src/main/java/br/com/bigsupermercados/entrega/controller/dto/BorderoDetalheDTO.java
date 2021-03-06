package br.com.bigsupermercados.entrega.controller.dto;

import java.util.List;
import java.util.Optional;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;

public class BorderoDetalheDTO {

	private Long codigo;
	private MotoristaDTO motorista;
	private List<GuiaBorderoDTO> guias;
	private List<LancamentoBorderoDTO> lancamentos;

	public BorderoDetalheDTO(Bordero bordero) {
		this.codigo = bordero.getCodigo();
		this.motorista = MotoristaDTO.converter(bordero.getMotorista());
		this.guias = GuiaBorderoDTO.converter(bordero.getGuias());
		this.lancamentos = LancamentoBorderoDTO.converter(bordero.getLancamentos());
	}

	public Long getCodigo() {
		return codigo;
	}

	public MotoristaDTO getMotorista() {
		return motorista;
	}

	public List<GuiaBorderoDTO> getGuias() {
		return guias;
	}

	public List<LancamentoBorderoDTO> getLancamentos() {
		return lancamentos;
	}

	public static BorderoDetalheDTO converter(Optional<Bordero> bordero) {
		return new BorderoDetalheDTO(bordero.get());
	}
}
