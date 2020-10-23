package br.com.bigsupermercados.entrega.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeracaoDeSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("123456"));
	}
}


//loja02 28uw