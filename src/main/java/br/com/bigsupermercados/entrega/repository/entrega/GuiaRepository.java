package br.com.bigsupermercados.entrega.repository.entrega;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.Bordero;
import br.com.bigsupermercados.entrega.modelo.entrega.Guia;

@Repository
public interface GuiaRepository extends JpaRepository<Guia, Long> {

	List<Guia> findByMotoristaIsNull();

	List<Bordero> findByBordero_Codigo(Long codigo);

	Page<Guia> findByDataAndLoja_CodigoAndPdvAndCupomAndValor(LocalDate data, Long loja, Integer pdv, String cupom,
			BigDecimal valor, Pageable paginacao);
}
