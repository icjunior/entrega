package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;

@Service
public class SelecaoLojaService {

	@Autowired
	private Lojas repository;

	public Page<Loja> buscarPaginado(Pageable pageable) {
		List<Loja> lojas = repository.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Loja> lojasList = new ArrayList<>();

		if (lojas.size() < startItem) {
			lojas = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, lojas.size());
			lojasList = lojas.subList(startItem, toIndex);
		}

		Page<Loja> lojaPage = new PageImpl<Loja>(lojasList, PageRequest.of(currentPage, pageSize), lojas.size());

		return lojaPage;
	}
}
