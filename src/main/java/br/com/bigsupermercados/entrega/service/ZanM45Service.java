package br.com.bigsupermercados.entrega.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.zanthus.ZanM45;
import br.com.bigsupermercados.entrega.repository.zanthus.ZanM45Repository;

@Service
public class ZanM45Service {

	@Autowired
	private ZanM45Repository repository;

	public Optional<ZanM45> buscarCupom(LocalDate data, Integer loja, Integer pdv, Integer cupom) {
		return repository.findByZanM45PK_M00afAndZanM45PK_M00zaAndZanM45PK_M00acAndZanM45PK_M00ad(data, loja, pdv,
				cupom);
	}

}
