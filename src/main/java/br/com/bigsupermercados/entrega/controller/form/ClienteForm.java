package br.com.bigsupermercados.entrega.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;
import br.com.bigsupermercados.entrega.repository.entrega.EnderecoRepository;

public class ClienteForm {

	private Long codigo;

	@NotBlank(message = "O nome não pode ser em branco")
	private String nome;

	@NotBlank(message = "O CPF não pode ser em branco")
	private String cpf;

	@NotNull(message = "A data de nascimento precisa ser preenchida")
	@Past(message = "A data de nascimento não pode ser no futuro")
	private LocalDate dataNascimento;

	@NotBlank(message = "O número de telefone não pode ser em branco")
	private String telefone;

	@NotBlank(message = "O CEP não pode ser em branco")
	private String cep;

	@NotBlank(message = "O número do endereço não pode ser em branco")
	private String numero;

	private String complemento;

	public ClienteForm() {
	}

	public ClienteForm(Long codigo, String nome, String cpf, LocalDate dataNascimento, String telefone, String cep,
			String numero, String complemento) {
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Cliente converter(EnderecoRepository enderecoRepository) {
		Endereco endereco = enderecoRepository.findByCep(cep.replace("-", "")).get();
		return new Cliente(codigo, nome, cpf, dataNascimento, telefone, endereco, numero, complemento);
	}
}
