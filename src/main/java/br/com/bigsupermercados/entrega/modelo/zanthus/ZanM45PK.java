package br.com.bigsupermercados.entrega.modelo.zanthus;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ZanM45PK implements Serializable {

	private static final long serialVersionUID = 4113538174784959523L;

	@Column(name = "m00af")
	private LocalDate m00af;

	@Column(name = "m00za")
	private Integer m00za;

	@Column(name = "m00ac")
	private Integer m00ac;

	@Column(name = "m00_cro")
	private Integer m00Cro;

	@Column(name = "m00ad")
	private Integer m00ad;

	@Column(name = "m00_trn")
	private Integer m00Trn;

	@Column(name = "m45ab")
	private String m45ab;

	@Column(name = "m45zc")
	private Integer m45zc;

	public LocalDate getM00af() {
		return m00af;
	}

	public void setM00af(LocalDate m00af) {
		this.m00af = m00af;
	}

	public Integer getM00za() {
		return m00za;
	}

	public void setM00za(Integer m00za) {
		this.m00za = m00za;
	}

	public Integer getM00ac() {
		return m00ac;
	}

	public void setM00ac(Integer m00ac) {
		this.m00ac = m00ac;
	}

	public Integer getM00Cro() {
		return m00Cro;
	}

	public void setM00Cro(Integer m00Cro) {
		this.m00Cro = m00Cro;
	}

	public Integer getM00ad() {
		return m00ad;
	}

	public void setM00ad(Integer m00ad) {
		this.m00ad = m00ad;
	}

	public Integer getM00Trn() {
		return m00Trn;
	}

	public void setM00Trn(Integer m00Trn) {
		this.m00Trn = m00Trn;
	}

	public String getM45ab() {
		return m45ab;
	}

	public void setM45ab(String m45ab) {
		this.m45ab = m45ab;
	}

	public Integer getM45zc() {
		return m45zc;
	}

	public void setM45zc(Integer m45zc) {
		this.m45zc = m45zc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((m00Cro == null) ? 0 : m00Cro.hashCode());
		result = prime * result + ((m00Trn == null) ? 0 : m00Trn.hashCode());
		result = prime * result + ((m00ac == null) ? 0 : m00ac.hashCode());
		result = prime * result + ((m00ad == null) ? 0 : m00ad.hashCode());
		result = prime * result + ((m00af == null) ? 0 : m00af.hashCode());
		result = prime * result + ((m00za == null) ? 0 : m00za.hashCode());
		result = prime * result + ((m45ab == null) ? 0 : m45ab.hashCode());
		result = prime * result + ((m45zc == null) ? 0 : m45zc.hashCode());
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
		ZanM45PK other = (ZanM45PK) obj;
		if (m00Cro == null) {
			if (other.m00Cro != null)
				return false;
		} else if (!m00Cro.equals(other.m00Cro))
			return false;
		if (m00Trn == null) {
			if (other.m00Trn != null)
				return false;
		} else if (!m00Trn.equals(other.m00Trn))
			return false;
		if (m00ac == null) {
			if (other.m00ac != null)
				return false;
		} else if (!m00ac.equals(other.m00ac))
			return false;
		if (m00ad == null) {
			if (other.m00ad != null)
				return false;
		} else if (!m00ad.equals(other.m00ad))
			return false;
		if (m00af == null) {
			if (other.m00af != null)
				return false;
		} else if (!m00af.equals(other.m00af))
			return false;
		if (m00za == null) {
			if (other.m00za != null)
				return false;
		} else if (!m00za.equals(other.m00za))
			return false;
		if (m45ab == null) {
			if (other.m45ab != null)
				return false;
		} else if (!m45ab.equals(other.m45ab))
			return false;
		if (m45zc == null) {
			if (other.m45zc != null)
				return false;
		} else if (!m45zc.equals(other.m45zc))
			return false;
		return true;
	}
}
