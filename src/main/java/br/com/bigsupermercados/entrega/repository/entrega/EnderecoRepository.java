package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Optional<Endereco> findByCep(String cep);

	List<Endereco> findByLogradouroContaining(String logradouro);

	List<Endereco> findByLogradouroContainingAndBairro_Cidade_Nome(String logradouro, String cidade);

	@Query(value = "select * " + "from endereco inner join bairro on endereco.bairro_codigo = bairro.codigo "
			+ "inner join cidade on bairro.cidade_codigo = cidade.codigo "
			+ "where cidade_codigo = :cidade and logradouro like %:logradouro% COLLATE SQL_Latin1_General_CP1_CI_AI", nativeQuery = true)
	List<Endereco> buscar(@Param("logradouro") String logradouro, @Param("cidade") Long cidade);

	@Query("SELECT e FROM Endereco e WHERE e.bairro.cidade.habilitaConsulta = true "
			+ "ORDER BY e.bairro.cidade.nome, e.bairro.nome, e.logradouro")
	List<Endereco> buscarPorCidadesAtivas();

	List<Endereco> findTop100ByBairro_Cidade_HabilitaConsultaIsTrue();
}
