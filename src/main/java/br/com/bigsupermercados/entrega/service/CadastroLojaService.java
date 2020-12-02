package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.entrega.Loja;
import br.com.bigsupermercados.entrega.repository.entrega.Lojas;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroLojaService {

	@Autowired
	private Lojas repository;

	@Transactional
	public void salvar(Loja loja) {
		repository.save(loja);
	}

	@Transactional
	public void excluir(Loja loja) {
		try {
			repository.delete(loja);
			repository.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cerveja. Já foi usada em algum momento");
		}
	}

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
