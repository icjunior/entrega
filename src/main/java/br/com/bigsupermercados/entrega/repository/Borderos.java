package br.com.bigsupermercados.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.Bordero;

@Repository
public interface Borderos extends JpaRepository<Bordero, Long> {

}
