package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;

@Repository
public interface Motoristas extends JpaRepository<Motorista, Long> {

	List<Motorista> findByAtivoTrue();
}
