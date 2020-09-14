package br.com.bigsupermercados.entrega.repository.entrega;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bigsupermercados.entrega.modelo.entrega.LancamentoBordero;

public interface LancamentoBorderoRepository extends JpaRepository<LancamentoBordero, Long> {

	List<LancamentoBordero> findByBordero_Codigo(Long bordero);

}
