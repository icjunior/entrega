package br.com.bigsupermercados.entrega.security;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeracaoDeSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123456"));
		
		
		BigDecimal maximoPontos = new BigDecimal(840);
		BigDecimal pontosAtuais = new BigDecimal(5);
		BigDecimal porcentagem = new BigDecimal(100);
				
		BigDecimal calculo = pontosAtuais.multiply(porcentagem);
		
		System.out.println(calculo);
		
		BigDecimal valorFinal = calculo.divide(maximoPontos, 2, RoundingMode.FLOOR);
			
		System.out.println(valorFinal);
	}
}


//loja02 28uw