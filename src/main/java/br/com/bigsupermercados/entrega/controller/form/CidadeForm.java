package br.com.bigsupermercados.entrega.controller.form;

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;

public class CidadeForm {

	private Long codigo;
	private String nome;
	private boolean habilitaConsulta;

	public CidadeForm() {
	}

	public CidadeForm(Cidade cidade) {
		this.codigo = cidade.getCodigo();
		this.nome = cidade.getNome();
		this.habilitaConsulta = cidade.isHabilitaConsulta();
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

	public boolean isHabilitaConsulta() {
		return habilitaConsulta;
	}

	public void setHabilitaConsulta(boolean habilitaConsulta) {
		this.habilitaConsulta = habilitaConsulta;
	}

//	public Cidade converter() {
//		return new Cidade(this.codigo, this.nome, this.habilitaConsulta);
//	}
}
