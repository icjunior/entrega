package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;

@Repository
public interface Borderos extends JpaRepository<Bordero, Long> {

	Optional<Bordero> findByMotorista_CodigoAndAbertoTrue(Long codigo);

	List<Bordero> findByAbertoFalse();

}
