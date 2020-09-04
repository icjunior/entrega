package br.com.bigsupermercados.entrega.security;

import java.math.BigDecimal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeracaoDeSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
		
		
		BigDecimal valor = new BigDecimal(119.49);
		BigDecimal porcentagem = new BigDecimal(5);
		
		System.out.println(
					valor
						.multiply(porcentagem)
						.divide(new BigDecimal(100))
						.setScale(2, BigDecimal.ROUND_DOWN)
				);
	}
}
