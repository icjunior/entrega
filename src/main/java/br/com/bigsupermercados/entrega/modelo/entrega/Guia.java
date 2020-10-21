package br.com.bigsupermercados.entrega.modelo.entrega;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "guia")
@DynamicUpdate
public class Guia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	private Loja loja;

	@Column(name = "data")
	private LocalDate data;

	private Integer pdv;
	private String cupom;
	private BigDecimal valor;

	@ManyToOne
	private Cliente cliente;

	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String complemento;

	@ManyToOne
	private Motorista motorista;

	@ManyToOne
	private Bordero bordero;

	private boolean reentrega = false;

	@Column(name = "porcentagem")
	private BigDecimal porcentagem;

	@Column(name = "chave_acesso")
	private String chaveAcesso;

	@Column(name = "excluido")
	private boolean excluido = false;

	@Column(name = "validado")
	private boolean validado = false;

	public Guia() {
	}

	public Guia(Long codigo, LocalDate data, Loja loja, Integer pdv, String cupom, Cliente cliente, String cep,
			String endereco, String numero, String bairro, String cidade, String complemento, Motorista motorista,
			Bordero bordero, boolean reentrega, BigDecimal valor, BigDecimal porcentagem, String chaveAcesso) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.loja = loja;
		this.pdv = pdv;
		this.cupom = cupom;
		this.cliente = cliente;
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.complemento = complemento;
		this.reentrega = reentrega;
		this.valor = valor;
		this.porcentagem = porcentagem;
		this.chaveAcesso = chaveAcesso;
	}
	
	public Guia(Guia guia) {
		super();
		this.loja = guia.getLoja();
		this.data = guia.getData();
		this.pdv = guia.getPdv();
		this.cupom = guia.getCupom();
		this.valor = guia.getValor();
		this.cliente = guia.getCliente();
		this.cep = guia.getCep();
		this.endereco = guia.getEndereco();
		this.numero = guia.getNumero();
		this.bairro = guia.getBairro();
		this.cidade = guia.getCidade();
		this.complemento = guia.getComplemento();
		this.motorista = guia.getMotorista();
		this.bordero = guia.getBordero();
		this.reentrega = guia.isReentrega();
		this.porcentagem = guia.getPorcentagem();
		this.chaveAcesso = guia.getChaveAcesso();
		this.excluido = guia.isExcluido();
		this.validado = guia.isValidado();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Integer getPdv() {
		return pdv;
	}

	public void setPdv(Integer pdv) {
		this.pdv = pdv;
	}

	public String getCupom() {
		return cupom;
	}

	public void setCupom(String cupom) {
		this.cupom = cupom;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public Bordero getBordero() {
		return bordero;
	}

	public void setBordero(Bordero bordero) {
		this.bordero = bordero;
	}

	public boolean isReentrega() {
		return reentrega;
	}

	public void setReentrega(boolean reentrega) {
		this.reentrega = reentrega;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
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
		Guia other = (Guia) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
