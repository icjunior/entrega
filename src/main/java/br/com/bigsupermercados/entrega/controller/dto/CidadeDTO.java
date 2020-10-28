package br.com.bigsupermercados.entrega.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;

public class CidadeDTO {

	private Long codigo;
	private String nome;
	private String estado;
	private boolean habilitaConsulta;

	public CidadeDTO(Cidade cidade) {
		this.codigo = cidade.getCodigo();
		this.nome = cidade.getNome();
		this.estado = cidade.getEstado().getNome();
		this.habilitaConsulta = cidade.isHabilitaConsulta();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getEstado() {
		return estado;
	}

	public boolean isHabilitaConsulta() {
		return habilitaConsulta;
	}

	public static List<CidadeDTO> converter(List<Cidade> cidades) {
		return cidades.stream().map(CidadeDTO::new).collect(Collectors.toList());
	}
}
