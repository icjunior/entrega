package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
	private BigDecimal porcentagem;
	private String complemento;

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
		this.porcentagem = cliente.getEndereco().getBairro().getPorcentagem();
		this.complemento = cliente.getComplemento();
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

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public String getComplemento() {
		return complemento;
	}

	public static ClienteDTO converter(Cliente cliente) {
		return new ClienteDTO(cliente);
	}

	public static List<ClienteDTO> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}
}
