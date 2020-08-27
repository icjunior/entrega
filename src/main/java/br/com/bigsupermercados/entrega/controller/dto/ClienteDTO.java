package br.com.bigsupermercados.entrega.controller.dto;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;

public class ClienteDTO {

	private String nome;
	private String cpf;
	private String telefone;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;

	public ClienteDTO(Cliente cliente) {
		super();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.telefone = cliente.getTelefone();
		this.cep = cliente.getEndereco().getCep();
		this.endereco = cliente.getEndereco().getLogradouro();
		this.numero = cliente.getNumero();
		this.bairro = cliente.getEndereco().getBairro().getNome();
		this.cidade = cliente.getEndereco().getBairro().getCidade().getNome();
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public static ClienteDTO converter(Cliente cliente) {
		return new ClienteDTO(cliente);
	}
}
