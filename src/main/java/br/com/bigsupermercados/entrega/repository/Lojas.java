package br.com.bigsupermercados.entrega.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.Loja;

@Repository
public interface Lojas extends JpaRepository<Loja, Long>{

}
