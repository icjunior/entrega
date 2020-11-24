package br.com.bigsupermercados.entrega.controller.dto;

import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;

public class MotoristaDTO {

	private Long codigo;
	private String nome;
	
	public MotoristaDTO(Motorista motorista) {
		this.codigo = motorista.getCodigo();
		this.nome = motorista.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public static MotoristaDTO converter(Motorista motorista) {
		return new MotoristaDTO(motorista);
	}

}
