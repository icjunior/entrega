package br.com.bigsupermercados.entrega.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.Usuario;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);

}
