package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Usuario;
import br.com.bigsupermercados.entrega.repository.entrega.helper.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	Optional<Usuario> findByLogin(String login);

	Optional<Usuario> findByLoginIgnoreCase(String login);
	
	List<Usuario> findByCodigoIn(Long[] codigos);
}
