package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>{

	@Query("SELECT b FROM Bairro b WHERE b.cidade.habilitaConsulta = true")
	List<Bairro> buscarBairrosHabilitados();
	
	List<Bairro> findTop100ByCidade_HabilitaConsultaIsTrue();
	
	List<Bairro> findByNomeContainingAndCidade_CodigoAndCidade_HabilitaConsultaTrue(String bairro, Long cidade);

	List<Bairro> findByCidade_Codigo(Long codigoCidade);
}
