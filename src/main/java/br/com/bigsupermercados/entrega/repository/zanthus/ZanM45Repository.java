package br.com.bigsupermercados.entrega.repository.zanthus;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45;
import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45PK;

@Repository
public interface ZanM45Repository extends JpaRepository<ZanM45, ZanM45PK> {

	Optional<ZanM45> findByZanM45PK_M00afAndZanM45PK_M00zaAndZanM45PK_M00acAndZanM45PK_M00ad(LocalDate data,
			Integer loja, Integer pdv, Integer cupom);

}
