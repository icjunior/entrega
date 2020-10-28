package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Bairro;

public class BairroDTO {

	private Long codigo;
	private String nome;
	private String cidade;
	private BigDecimal porcentagem;
	
	public BairroDTO(Bairro bairro) {
		this.codigo = bairro.getCodigo();
		this.nome = bairro.getNome();
		this.cidade = bairro.getCidade().getNome();
		this.porcentagem = bairro.getPorcentagem();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public String getCidade() {
		return cidade;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public static List<BairroDTO> converter(List<Bairro> bairros) {
		return bairros.stream().map(BairroDTO::new).collect(Collectors.toList());
	}

}
