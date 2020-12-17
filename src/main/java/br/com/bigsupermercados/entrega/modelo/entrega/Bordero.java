package br.com.bigsupermercados.entrega.modelo.entrega;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
	private LocalDateTime dataHoraLancamento = LocalDateTime.now();

	@Column(name = "aberto")
	private boolean aberto = true;

	@OneToMany(mappedBy = "bordero", fetch = FetchType.EAGER)
	private List<Guia> guias;

	@OneToMany(mappedBy = "bordero")
	private List<LancamentoBordero> lancamentos;

	@Column(name = "data_hora_fechamento")
	private LocalDateTime datahoraFechamento;

	public Bordero() {

	}

	public Bordero(Motorista motorista) {
		super();
		this.motorista = motorista;
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

	public BigDecimal getValorAReceberBruto() {
		return guias.stream().map(guia -> guia.getValor().multiply(guia.getPorcentagem()).divide(new BigDecimal(100))
				.setScale(2, BigDecimal.ROUND_DOWN)).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getValorAcrescimo() {
		return lancamentos.stream()
				.filter(lancamento -> lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.ACRESCIMO)
				.map(lancamento -> lancamento.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getValorDescontos() {
		return lancamentos.stream()
				.filter(lancamento -> lancamento.getTipoLancamento().getModoLancamento() == ModoLancamento.DESCONTO)
				.map(lancamento -> lancamento.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal getValorLiquido() {
		return getValorAReceberBruto().add(getValorAcrescimo()).subtract(getValorDescontos());
	}

	public BigDecimal getValorArredondamento() {
		BigDecimal fatorArredondamento = new BigDecimal("5");
		BigDecimal valorBorderoSemArredondamento = getValorLiquido();
		BigDecimal valorBorderoArredondado = valorBorderoSemArredondamento.divide(fatorArredondamento)
				.setScale(0, RoundingMode.UP).multiply(fatorArredondamento);

		return valorBorderoArredondado.subtract(valorBorderoSemArredondamento);
	}

	public LocalDateTime getDatahoraFechamento() {
		return datahoraFechamento;
	}

	public void setDatahoraFechamento(LocalDateTime datahoraFechamento) {
		this.datahoraFechamento = datahoraFechamento;
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
