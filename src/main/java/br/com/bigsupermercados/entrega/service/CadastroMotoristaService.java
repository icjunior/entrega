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

import br.com.bigsupermercados.entrega.modelo.entrega.Motorista;
import br.com.bigsupermercados.entrega.repository.entrega.Motoristas;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroMotoristaService {

	@Autowired
	private Motoristas repository;

	@Transactional
	public void salvar(Motorista motorista) {
		repository.save(motorista);
	}

	@Transactional
	public void excluir(Motorista motorista) {
		try {
			repository.delete(motorista);
			repository.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException(
					"Impossível apagar motorista. Já foi utilizado em algum momento");
		}
	}

	public Page<Motorista> buscarPaginado(Pageable pageable) {
		List<Motorista> motoristas = repository.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Motorista> motoristasList = new ArrayList<>();

		if (motoristas.size() < startItem) {
			motoristas = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, motoristas.size());
			motoristasList = motoristas.subList(startItem, toIndex);
		}

		Page<Motorista> motoristaPage = new PageImpl<Motorista>(motoristasList, PageRequest.of(currentPage, pageSize),
				motoristas.size());

		return motoristaPage;
	}
}
