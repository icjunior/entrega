package br.com.bigsupermercados.entrega.modelo.entrega;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bigsupermercados.entrega.controller.form.TipoLancamentoForm;

@Entity
@Table(name = "tipo_lancamento")
public class TipoLancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "nome")
	private String nome;

	@Column(name = "ativo")
	private boolean ativo = true;

	@Enumerated(EnumType.STRING)
	@Column(name = "modo_lancamento")
	private ModoLancamento modoLancamento;

	public TipoLancamento() {

	}

	public TipoLancamento(Long codigo, String nome, boolean ativo, ModoLancamento modoLancamento) {
		super();
		this.codigo = codigo;
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

	public ModoLancamento getModoLancamento() {
		return modoLancamento;
	}

	public void setModoLancamento(ModoLancamento modoLancamento) {
		this.modoLancamento = modoLancamento;
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
		TipoLancamento other = (TipoLancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public TipoLancamentoForm converter() {
		return new TipoLancamentoForm(nome, ativo,
				modoLancamento.getDescricao() == ModoLancamento.ACRESCIMO.getDescricao() ? true : false);
	}

}
