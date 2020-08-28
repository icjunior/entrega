package br.com.bigsupermercados.entrega.modelo.zanthus;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zan_m45")
public class ZanM45 {

	@EmbeddedId
	private ZanM45PK zanM45PK;

	@Column(name = "m45ah")
	private Integer m45ah;

	@Column(name = "m45ak")
	private BigDecimal m45ak;

	public ZanM45PK getZanM45PK() {
		return zanM45PK;
	}

	public void setZanM45PK(ZanM45PK zanM45PK) {
		this.zanM45PK = zanM45PK;
	}

	public Integer getM45ah() {
		return m45ah;
	}

	public void setM45ah(Integer m45ah) {
		this.m45ah = m45ah;
	}

	public BigDecimal getM45ak() {
		return m45ak;
	}

	public void setM45ak(BigDecimal m45ak) {
		this.m45ak = m45ak;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zanM45PK == null) ? 0 : zanM45PK.hashCode());
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
		ZanM45 other = (ZanM45) obj;
		if (zanM45PK == null) {
			if (other.zanM45PK != null)
				return false;
		} else if (!zanM45PK.equals(other.zanM45PK))
			return false;
		return true;
	}
}
