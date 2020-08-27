package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Optional<Endereco> findByCep(String cep);
}
