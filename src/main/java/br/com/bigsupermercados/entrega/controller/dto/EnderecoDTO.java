package br.com.bigsupermercados.entrega.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;

public class EnderecoDTO {

	private Long codigo;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;

	public EnderecoDTO(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.bairro = endereco.getBairro().getNome();
		this.cidade = endereco.getBairro().getCidade().getNome();
		this.codigo = endereco.getCodigo();
		this.bairro = endereco.getBairro().getNome();
		this.cep = endereco.getCep();
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getCep() {
		return cep;
	}

	public static EnderecoDTO converter(Endereco endereco) {
		return new EnderecoDTO(endereco);
	}

	public static List<EnderecoDTO> converter(List<Endereco> enderecos) {
		return enderecos.stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}
}