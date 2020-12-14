package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;

@Service
public class SelecaoClienteService {

	@Autowired
	private Clientes repository;

	public Optional<Cliente> buscaPorCPF(String cpf) {
		return repository.findByCpf(cpf);
	}

	public List<Cliente> buscaPorNome(String nome) {
		return repository.findByNomeContaining(nome);
	}

	public Page<Cliente> buscarPaginado(Pageable pageable) {
		List<Cliente> clientes = repository.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;

		List<Cliente> clientesList = new ArrayList<>();

		if (clientes.size() < startItem) {
			clientes = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, clientes.size());
			clientesList = clientes.subList(startItem, toIndex);
		}

		Page<Cliente> clientePage = new PageImpl<Cliente>(clientesList, PageRequest.of(currentPage, pageSize),
				clientes.size());

		return clientePage;
	}
}
