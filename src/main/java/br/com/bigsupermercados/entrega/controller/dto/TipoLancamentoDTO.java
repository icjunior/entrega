package br.com.bigsupermercados.entrega.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;

public class TipoLancamentoDTO {

	private Long codigo;
	private String nome;
	private boolean ativo;
	private String modoLancamento;

	public TipoLancamentoDTO(TipoLancamento tipoLancamento) {
		this.codigo = tipoLancamento.getCodigo();
		this.nome = tipoLancamento.getNome();
		this.ativo = tipoLancamento.isAtivo();
		this.modoLancamento = tipoLancamento.getModoLancamento().getDescricao();
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public String getModoLancamento() {
		return modoLancamento;
	}

	public static List<TipoLancamentoDTO> converter(List<TipoLancamento> tiposLancamento) {
		return tiposLancamento.stream().map(TipoLancamentoDTO::new).collect(Collectors.toList());
	}

}
