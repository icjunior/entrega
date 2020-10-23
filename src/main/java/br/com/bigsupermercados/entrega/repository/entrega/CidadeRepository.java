package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	@Query("SELECT c FROM Cidade c WHERE codigo IN (8979,8928,8992,9492,9287,9338,9396) ORDER BY nome")
	List<Cidade> cidadesAtendidas();
}
