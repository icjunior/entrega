package br.com.bigsupermercados.entrega.controller.form;

import javax.validation.constraints.NotBlank;

import br.com.bigsupermercados.entrega.modelo.entrega.ModoLancamento;
import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;

public class TipoLancamentoForm {

	private Long codigo;
	
	@NotBlank(message = "O nome do tipo de lançamento não pode ser em branco")
	private String nome;
	
	private boolean ativo;
	private boolean modoLancamento;
	
	public TipoLancamentoForm() {
		
	}
	
	public TipoLancamentoForm(String nome, boolean ativo, boolean modoLancamento) {
		this.nome = nome;
		this.ativo = ativo;
		this.modoLancamento = modoLancamento;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean getModoLancamento() {
		return modoLancamento;
	}

	public void setModoLancamento(boolean modoLancamento) {
		this.modoLancamento = modoLancamento;
	}

	public TipoLancamento converter() {
		return new TipoLancamento(codigo, nome, ativo, trataModoLancamento());
	}

	private ModoLancamento trataModoLancamento() {
		return modoLancamento ? ModoLancamento.ACRESCIMO : ModoLancamento.DESCONTO;
	}
}
