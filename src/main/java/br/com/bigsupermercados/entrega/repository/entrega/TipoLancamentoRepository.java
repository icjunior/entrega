package br.com.bigsupermercados.entrega.repository.entrega;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.entrega.TipoLancamento;

@Repository
public interface TipoLancamentoRepository extends JpaRepository<TipoLancamento, Long> {

	TipoLancamento findByNome(String nome);

}
