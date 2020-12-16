package br.com.bigsupermercados.entrega.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Usuario;
import br.com.bigsupermercados.entrega.security.UsuarioSistema;

@Service
public class UsuarioAutenticadoService {

	public static Usuario usuarioAutenticado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		return usuarioSistema.getUsuario();
	}
}
