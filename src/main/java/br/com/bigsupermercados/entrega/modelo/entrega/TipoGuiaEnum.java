package br.com.bigsupermercados.entrega.modelo.entrega;

public enum TipoGuiaEnum {

	CUPOM_PDV("Cupom"), NOTA_FISCAL("Nota fiscal");

	private String descricao;

	private TipoGuiaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
