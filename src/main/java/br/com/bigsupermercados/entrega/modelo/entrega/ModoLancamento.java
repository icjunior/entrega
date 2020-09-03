package br.com.bigsupermercados.entrega.modelo.entrega;

public enum ModoLancamento {

	ACRESCIMO("Acréscimo"), DESCONTO("Desconto");

	private String descricao;

	private ModoLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
