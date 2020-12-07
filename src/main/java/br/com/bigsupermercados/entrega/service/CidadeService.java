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

import br.com.bigsupermercados.entrega.modelo.entrega.Cidade;
import br.com.bigsupermercados.entrega.repository.entrega.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public List<Cidade> lista() {
		return repository.buscar();
	}

	@Transactional
	public void salvar(Cidade cidade) {
		repository.save(cidade);
	}

	public Page<Cidade> buscarPaginado(Pageable pageable) {
		List<Cidade> cidades = repository.findTop100ByHabilitaConsultaIsTrue();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Cidade> cidadesList = new ArrayList<>();

		if (cidades.size() < startItem) {
			cidades = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, cidades.size());
			cidadesList = cidades.subList(startItem, toIndex);
		}

		Page<Cidade> page = new PageImpl<Cidade>(cidadesList, PageRequest.of(currentPage, pageSize), cidades.size());

		return page;
	}
}
