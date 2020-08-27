package br.com.bigsupermercados.entrega.controller.dto;

import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;

public class EnderecoDTO {

	private String endereco;
	private String bairro;
	private String cidade;

	public EnderecoDTO(Endereco endereco) {
		this.endereco = endereco.getLogradouro();
		this.bairro = endereco.getBairro().getNome();
		this.cidade = endereco.getBairro().getCidade().getNome();
	}

	public String getEndereco() {
		return endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public static EnderecoDTO converter(Endereco endereco) {
		return new EnderecoDTO(endereco);
	}
}