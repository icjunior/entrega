package br.com.bigsupermercados.entrega.modelo.entrega;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "bordero")
@DynamicUpdate
public class Bordero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	private Motorista motorista;

	@Column(name = "data_hora_lancamento")
	private LocalDateTime dataHoraLancamento;

	@Column(name = "aberto", columnDefinition = "boolean default true")
	private boolean aberto;

	@OneToMany(mappedBy = "bordero", fetch = FetchType.EAGER)
	private List<Guia> guias;

	@OneToMany(mappedBy = "bordero")
	private List<LancamentoBordero> lancamentos;

	public Bordero() {

	}

	public Bordero(Motorista motorista, LocalDateTime dataHoraLancamento, boolean aberto) {
		super();
		this.motorista = motorista;
		this.dataHoraLancamento = dataHoraLancamento;
		this.aberto = aberto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Motorista getMotorista() {
		return motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}

	public LocalDateTime getDataHoraLancamento() {
		return dataHoraLancamento;
	}

	public void setDataHoraLancamento(LocalDateTime dataHoraLancamento) {
		this.dataHoraLancamento = dataHoraLancamento;
	}

	public boolean isAberto() {
		return aberto;
	}

	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public List<Guia> getGuias() {
		return guias;
	}

	public void setGuias(List<Guia> guias) {
		this.guias = guias;
	}

	public List<LancamentoBordero> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoBordero> lancamentos) {
		this.lancamentos = lancamentos;
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
		Bordero other = (Bordero) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
