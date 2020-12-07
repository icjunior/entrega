package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Bairro;
import br.com.bigsupermercados.entrega.repository.entrega.BairroRepository;

@Service
public class BairroService {

	@Autowired
	private BairroRepository repository;

	public List<Bairro> lista() {
		return repository.buscarBairrosHabilitados();
	}

	@Transactional
	public void salvar(Bairro bairro) {
		repository.save(bairro);
	}

	public Page<Bairro> buscarPaginado(Pageable pageable) {
		List<Bairro> bairros = repository.findTop100ByCidade_HabilitaConsultaIsTrue();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Bairro> bairrosList = new ArrayList<>();

		if (bairros.size() < startItem) {
			bairros = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, bairros.size());
			bairrosList = bairros.subList(startItem, toIndex);
		}

		Page<Bairro> page = new PageImpl<Bairro>(bairrosList, PageRequest.of(currentPage, pageSize), bairros.size());

		return page;
	}
}
