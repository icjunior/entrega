package br.com.bigsupermercados.entrega.security;

import java.math.BigDecimal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeracaoDeSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123456"));
		
		BigDecimal valorTransacao = BigDecimal.ZERO;
		BigDecimal porcentagemPagSeguro = new BigDecimal(7.49);
		BigDecimal porcentagemMecPock = new BigDecimal(0.65);
	}
}


//loja02 28uw