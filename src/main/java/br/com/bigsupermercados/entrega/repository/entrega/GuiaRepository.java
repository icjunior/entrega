package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;

@Repository
public interface GuiaRepository extends JpaRepository<Guia, Long> {

	List<Guia> findByMotoristaIsNull();

	List<Bordero> findByBordero_Codigo(Long codigo);

}
