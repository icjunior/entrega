package br.com.bigsupermercados.entrega.controller.filter;

public class BairroFilter {

	private String bairro;
	private Long cidade;

	public String getBairro() {
		return bairro;
	}

	public Long getCidade() {
		return cidade;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCidade(Long cidade) {
		this.cidade = cidade;
	}
}
