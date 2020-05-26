package br.com.bigsupermercados.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.Movimento;

@Repository
public interface Movimentos extends JpaRepository<Movimento, Long> {

}
