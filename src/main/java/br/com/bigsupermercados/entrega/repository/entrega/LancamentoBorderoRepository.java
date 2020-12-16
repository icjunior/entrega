package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;

public interface LancamentoBorderoRepository extends JpaRepository<LancamentoBordero, Long> {

	List<LancamentoBordero> findByBordero_Codigo(Long bordero);

	Optional<LancamentoBordero> findByBordero_CodigoAndTipoLancamento_Nome(Long codigo, String arredondamento);

}
