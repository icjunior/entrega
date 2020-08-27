package br.com.bigsupermercados.entrega.repository.entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Loja;

@Repository
public interface Lojas extends JpaRepository<Loja, Long>{

}
