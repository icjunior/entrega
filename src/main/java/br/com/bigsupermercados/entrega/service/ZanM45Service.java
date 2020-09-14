package br.com.bigsupermercados.entrega.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;
import br.com.bigsupermercados.entrega.repository.zanthus.ZanM45Repository;

@Service
public class ZanM45Service {

	@Autowired
	private ZanM45Repository repository;

	@Autowired
	private Lojas lojaRepository;

	public Optional<ZanM45> buscarCupom(LocalDate data, Long loja, Integer pdv, Integer cupom) {

		Loja lojaConvertida = lojaRepository.findById(loja).get();

		return repository.findByZanM45PK_M00afAndZanM45PK_M00zaAndZanM45PK_M00acAndZanM45PK_M00ad(data,
				lojaConvertida.getLojaZanthus(), pdv, cupom);
	}

}
