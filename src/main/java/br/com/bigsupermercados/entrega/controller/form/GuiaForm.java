package br.com.bigsupermercados.entrega.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;

public class GuiaForm {

	private Long codigo;
	private LocalDate data;
	private Long codigoLoja;
	private Integer pdv;
	private String cupom;
	private BigDecimal valor;
	private String cpf;
	private String telefone;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String complemento;
	private BigDecimal porcentagem;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Long getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(Long codigoLoja) {
		this.codigoLoja = codigoLoja;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Guia converter(Clientes clienteRepository, Lojas lojaRepository) {
		Cliente cliente = clienteRepository.findByCpf(cpf).get();
		Loja loja = lojaRepository.findById(codigoLoja).get();

		return new Guia(codigo, data, loja, pdv, cupom, cliente, cep, endereco, numero, bairro, cidade, complemento,
				null, null, false, valor, porcentagem);
	}
}
