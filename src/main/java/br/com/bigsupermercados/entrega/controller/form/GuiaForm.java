package br.com.bigsupermercados.entrega.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;
import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;
import br.com.bigsupermercados.entrega.service.UsuarioAutenticadoService;

public class GuiaForm {

	private Long codigo;

	@NotNull(message = "O campo data do cupom não pode ser em branco")
	@PastOrPresent(message = "A data do cupom não pode ser no futuro")
	private LocalDate data;

	@Min(value = 1, message = "A loja não pode ser em branco")
	private Long codigoLoja;

	@NotNull(message = "O número do PDV não pode ser em branco")
	private Integer pdv;

	@NotBlank(message = "O número do cupom não pode ser em branco")
	private String cupom;

	@NotNull(message = "O valor do cupom não pode ser em branco")
	private BigDecimal valor;

	@NotBlank(message = "O CPF do cliente não pode ser em branco")
	private String cpf;

	private String telefone;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String complemento;
	private BigDecimal porcentagem;
	private String chaveAcesso;
	private String nome;

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

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Guia converter(Clientes clienteRepository, Lojas lojaRepository) {
		Cliente cliente = clienteRepository.findByCpf(cpf).get();
		Loja loja = UsuarioAutenticadoService.usuarioAutenticado().getLoja();

		return new Guia(codigo, data, loja, pdv, cupom, cliente, cep, endereco, numero, bairro, cidade, complemento,
				null, null, false, valor, porcentagem, chaveAcesso);
	}
}
