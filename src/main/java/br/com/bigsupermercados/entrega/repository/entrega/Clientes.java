package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente, Long>{

	Optional<Cliente> findByCpf(String cpf);

}
