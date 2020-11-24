package br.com.bigsupermercados.entrega.repository.entrega;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;

@Repository
public interface GuiaRepository extends JpaRepository<Guia, Long> {

	List<Guia> findByMotoristaIsNullAndExcluidoFalse();

	List<Bordero> findByBordero_Codigo(Long codigo);

	@Query("SELECT g FROM Guia g WHERE data = ?1 AND loja.codigo = ?2 AND pdv = ?3 AND cupom = ?4 AND valor = ?5 "
			+ "AND excluido = false")
	Page<Guia> buscarCupomComPaginacao(LocalDate data, Long loja, Integer pdv, String cupom, BigDecimal valor,
			Pageable paginacao);

	@Query("SELECT g FROM Guia g WHERE data = ?1 AND loja.codigo = ?2 AND pdv = ?3 AND cupom = ?4 AND valor = ?5 "
			+ "AND excluido = false")
	Optional<Guia> buscarCupom(LocalDate data, Long loja, Integer pdv, String cupom, BigDecimal valor);

	@Query("SELECT g FROM Guia g WHERE bordero.codigo = ?1 AND chaveAcesso = ?2 AND excluido = false AND validado = false")
	Optional<Guia> buscarCupomNoBordero(Long bordero, String chaveAcesso);

	@Query("SELECT g FROM Guia g WHERE motorista.codigo = ?1 AND (cupom = ?2 OR valor = ?3) "
			+ "AND bordero IS NULL AND excluido = false")
	Optional<Guia> buscarGuiaLiberada(Long motorista, String cupom, BigDecimal valor);
}
