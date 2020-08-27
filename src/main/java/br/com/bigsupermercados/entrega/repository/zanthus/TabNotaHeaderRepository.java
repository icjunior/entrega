package br.com.bigsupermercados.entrega.repository.zanthus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bigsupermercados.entrega.modelo.zanthus.TabNotaHeader;

@Repository
public interface TabNotaHeaderRepository extends JpaRepository<TabNotaHeader, Long> {

}
