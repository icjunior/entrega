package br.com.bigsupermercados.entrega.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "loja")
public class Loja {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Razão social não pode ser em branco")
	private String razao;

	@NotBlank(message = "Nome fantasia não pode ser em branco")
	private String apelido;

	@NotBlank(message = "CNPJ não pode ser em branco")
	private String cnpj;

	@NotBlank(message = "IE não pode ser em branco")
	private String ie;

	@NotBlank(message = "Telefone não pode ser em branco")
	private String telefone;

	@NotBlank(message = "Endereço não pode ser em branco")
	private String endereco;

	@NotBlank(message = "Número não pode ser em branco")
	private String numero;

	@NotBlank(message = "Bairro não pode ser em branco")
	private String bairro;

	@NotBlank(message = "Cidade não pode ser em branco")
	private String cidade;

	@NotBlank(message = "CEP não pode ser em branco")
	private String cep;

	private boolean ativo = true;

	@NotBlank(message = "Email do gerente não pode ser em branco")
	private String emailGerente;

	@NotBlank(message = "Email do supervisor não pode ser em branco")
	private String emailSupervisor;

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmailGerente() {
		return emailGerente;
	}

	public void setEmailGerente(String emailGerente) {
		this.emailGerente = emailGerente;
	}

	public String getEmailSupervisor() {
		return emailSupervisor;
	}

	public void setEmailSupervisor(String emailSupervisor) {
		this.emailSupervisor = emailSupervisor;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loja other = (Loja) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
