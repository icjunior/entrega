package br.com.bigsupermercados.entrega.modelo.entrega;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bordero_lancamento")
public class LancamentoBordero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "bordero_codigo")
	private Bordero bordero;

	@ManyToOne
	@JoinColumn(name = "tipo_lancamento_codigo")
	private TipoLancamento tipoLancamento;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "data_hora_inclusao")
	private LocalDateTime dataHoraInclusao;

	public LancamentoBordero() {
		
	}
	
	public LancamentoBordero(Bordero bordero, TipoLancamento tipoLancamento, BigDecimal valor,
			LocalDateTime dataHoraInclusao) {
		this.bordero = bordero;
		this.tipoLancamento = tipoLancamento;
		this.valor = valor;
		this.dataHoraInclusao = dataHoraInclusao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Bordero getBordero() {
		return bordero;
	}

	public void setBordero(Bordero bordero) {
		this.bordero = bordero;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getDataHoraInclusao() {
		return dataHoraInclusao;
	}

	public void setDataHoraInclusao(LocalDateTime dataHoraInclusao) {
		this.dataHoraInclusao = dataHoraInclusao;
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
		LancamentoBordero other = (LancamentoBordero) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
