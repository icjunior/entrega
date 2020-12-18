package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Usuario;
import br.com.bigsupermercados.entrega.repository.entrega.helper.UsuariosQueries;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

	Optional<Usuario> findByLogin(String login);

	Optional<Usuario> findByLoginIgnoreCase(String login);

	List<Usuario> findByCodigoIn(Long[] codigos);

	@Query("SELECT distinct p.nome FROM Usuario u INNER JOIN u.grupos g INNER JOIN g.permissoes p WHERE u = :usuario")
	List<String> permissoes(Usuario usuario);
}
