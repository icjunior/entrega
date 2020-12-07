package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.controller.filter.EnderecoFilter;
import br.com.bigsupermercados.entrega.modelo.entrega.Endereco;
import br.com.bigsupermercados.entrega.repository.entrega.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public Optional<Endereco> findByCEP(String cep) {
		return repository.findByCep(cep.replace("-", ""));
	}

	public List<Endereco> findByLogradouro(String logradouro, Long cidade) {
		return repository.buscar(logradouro, cidade);
	}

	public List<Endereco> findAll() {
		return repository.buscarPorCidadesAtivas();
	}

	@Transactional
	public void salvar(Endereco endereco) {
		repository.save(endereco);
	}

	public Page<Endereco> buscarPaginado(Pageable pageable, EnderecoFilter enderecoFilter) {

		List<Endereco> enderecos;

		if (enderecoFilter.getEndereco() == null || enderecoFilter.getEndereco() == "") {
			enderecos = repository.findTop100ByBairro_Cidade_HabilitaConsultaIsTrue();
		} else {
			String endereco = enderecoFilter.getEndereco();
			Long cidade = enderecoFilter.getCidade();
			enderecos = repository.buscar(endereco, cidade);
		}

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Endereco> enderecosList = new ArrayList<>();

		if (enderecos.size() < startItem) {
			enderecos = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, enderecos.size());
			enderecosList = enderecos.subList(startItem, toIndex);
		}

		Page<Endereco> page = new PageImpl<Endereco>(enderecosList, PageRequest.of(currentPage, pageSize),
				enderecos.size());

		return page;
	}
}
