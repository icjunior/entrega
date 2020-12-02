package br.com.bigsupermercados.entrega.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.bigsupermercados.entrega.modelo.entrega.Cliente;
import br.com.bigsupermercados.entrega.repository.entrega.Clientes;
import br.com.bigsupermercados.entrega.service.exception.ImpossivelExcluirEntidadeException;
import br.com.bigsupermercados.entrega.service.exception.RegistroJaCadastradoException;

@Service
public class CadastroClienteService {

	@Autowired
	private Clientes repository;

	@Transactional
	public void salvar(Cliente cliente) {
		Optional<Cliente> clienteOpt = repository.findByCpf(cliente.getCpf());

		if (cliente.getCodigo() == null && clienteOpt.isPresent()) {
			throw new RegistroJaCadastradoException("CPF já cadastrado no sistema");
		}

		repository.save(cliente);
	}

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			repository.delete(cliente);
			repository.flush();

		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar cliente. Já foi usada em algum momento");
		}
	}

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
