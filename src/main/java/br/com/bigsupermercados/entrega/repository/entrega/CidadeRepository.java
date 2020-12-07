package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("SELECT c FROM Cidade c WHERE habilitaConsulta = true ORDER BY nome")
	List<Cidade> cidadesAtendidas();

	@Query("SELECT c FROM Cidade c ORDER BY habilitaConsulta DESC, c.estado.nome, nome")
	List<Cidade> buscar();
	
	List<Cidade> findTop100ByHabilitaConsultaIsTrue();
}
