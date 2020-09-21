package br.com.bigsupermercados.entrega.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45;

public class ZanM45DTO {

	private LocalDate m00af;
	private Integer m00za;
	private Integer m00ac;
	private Integer m00ad;
	private BigDecimal m45ak;
	private String m45xb;

	public ZanM45DTO(LocalDate m00af, Integer m00za, Integer m00ac, Integer m00ad, BigDecimal m45ak, String m45xb) {
		this.m00af = m00af;
		this.m00za = m00za;
		this.m00ac = m00ac;
		this.m00ad = m00ad;
		this.m45ak = m45ak;
		this.m45xb = m45xb;
	}

	public LocalDate getM00af() {
		return m00af;
	}

	public Integer getM00za() {
		return m00za;
	}

	public Integer getM00ac() {
		return m00ac;
	}

	public Integer getM00ad() {
		return m00ad;
	}

	public BigDecimal getM45ak() {
		return m45ak;
	}

	public String getM45xb() {
		return m45xb;
	}

	public static ZanM45DTO converter(ZanM45 zanM45) {
		return new ZanM45DTO(zanM45.getZanM45PK().getM00af(), zanM45.getZanM45PK().getM00za(),
				zanM45.getZanM45PK().getM00ac(), zanM45.getZanM45PK().getM00ad(), zanM45.getM45ak(), zanM45.getM45xb());
	}
}
