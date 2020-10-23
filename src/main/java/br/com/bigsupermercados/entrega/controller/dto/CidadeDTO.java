package br.com.bigsupermercados.entrega.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;

public class CidadeDTO {

	private Long codigo;
	private String nome;

	public CidadeDTO(Cidade cidade) {
		this.codigo = cidade.getCodigo();
		this.nome = cidade.getNome();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public static List<CidadeDTO> converter(List<Cidade> cidades) {
		return cidades.stream().map(CidadeDTO::new).collect(Collectors.toList());
	}
}
