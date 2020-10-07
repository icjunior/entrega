package br.com.bigsupermercados.entrega.repository.entrega.helper;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bigsupermercados.entrega.modelo.entrega.Usuario;
import br.com.bigsupermercados.entrega.repository.entrega.filter.UsuarioFilter;

public interface UsuariosQueries {

	public Optional<Usuario> porLoginEAtivo(String login);
	
	public Page<Usuario> filtrar(UsuarioFilter filtro, Pageable pageable);
}