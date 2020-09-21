package br.com.bigsupermercados.entrega.security;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeracaoDeSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));

		BigDecimal valor = new BigDecimal("565.14");

		System.out.println(
				valor
					.divide(new BigDecimal("5"))
					.setScale(0, RoundingMode.UP)
					.multiply(new BigDecimal("5"))
				);
	}
}
