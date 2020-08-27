package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);

}
